package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor

public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    public UserDto findUserById(Long userId) {
        String sql = "SELECT * FROM USERS WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(UserDto.class));
    }

    public List<UserDto> findUsersByName(String name) {
        String sql = "SELECT * FROM Users WHERE name = ?";
        return jdbcTemplate.query(sql, new Object[]{name}, new BeanPropertyRowMapper<>(UserDto.class));
    }

    public List<UserDto> findUsersByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM Users WHERE phoneNumber = ?";
        return jdbcTemplate.query(sql, new Object[]{phoneNumber}, new BeanPropertyRowMapper<>(UserDto.class));
    }

    public List<UserDto> findUsersByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, new BeanPropertyRowMapper<>(UserDto.class));
    }

    public UserDto findEmployerById(Long userId) {
        String sql = "SELECT * FROM Users WHERE id = ? AND ACCOUNTTYPE = 'employer'";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(UserDto.class));
    }

    public UserDto findApplicantById(Long userId) {
        String sql = "SELECT * FROM Users WHERE id = ? AND ACCOUNTTYPE = 'applicant'";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(UserDto.class));
    }
}
