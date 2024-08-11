package kg.attractor.jobsearch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        String fetchUsers = """
                select email, password, enabled
                from users
                where email = ?;
                """;
        String fetchAuthorities = """
                select email, authority
                from authorities a, users u
                where u.AUTHORITY_ID = a.ID
                and email = ?;
                """;
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(fetchUsers)
                .authoritiesByUsernameQuery(fetchAuthorities);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->  request
                        .requestMatchers(HttpMethod.POST,"/users/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/users/update").authenticated()
                        .requestMatchers(HttpMethod.POST,"/resumes/create").hasAuthority("applicant")
                        .requestMatchers(HttpMethod.PUT, "/resumes/update").hasAuthority("applicant")
                        .requestMatchers(HttpMethod.DELETE,"/resumes/delete/{id}").hasAuthority("applicant")
                        .requestMatchers(HttpMethod.GET,"/resumes/category/{categoryId}").hasAuthority("employer")
                        .requestMatchers(HttpMethod.GET,"/resumes/all").hasAuthority("employer")
                        .requestMatchers(HttpMethod.GET,"/resumes/applicant/{applicantId}").hasAuthority("employer")
                        .requestMatchers(HttpMethod.POST,"/vacancies/create").hasAuthority("employer")
                        .requestMatchers(HttpMethod.PUT, "/vacancies/update").hasAuthority("employer")
                        .requestMatchers(HttpMethod.DELETE,"/vacancies/delete/{id}").hasAuthority("employer")
                        .requestMatchers(HttpMethod.GET,"/vacancies/all").permitAll()
                        .requestMatchers(HttpMethod.GET,"/vacancies/category/{categoryId}").hasAuthority("applicant")
                        .requestMatchers(HttpMethod.GET,"/vacancies/active").hasAuthority("applicant")
                        .requestMatchers(HttpMethod.POST,"/respondedApplicant/respond").hasAuthority("applicant")
                        .requestMatchers(HttpMethod.GET,"/respondedApplicant/vacancies/{applicantId}").hasAuthority("applicant")
                        .requestMatchers(HttpMethod.GET,"/respondedApplicant/vacancy/{vacancyId}").hasAuthority("employer")
                );
                return http.build();
    }
}
