package kg.attractor.jobsearch.service.impl.customImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import java.io.IOException;

public class AuthenticationSuccessServiceImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String targetUrl = "/";

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals("employer")) {
                targetUrl = "/resumes";
                break;
            } else if (authority.getAuthority().equals("applicant")) {
                targetUrl = "/vacancies";
                break;
            }
        }

        response.sendRedirect(targetUrl);
    }
}
