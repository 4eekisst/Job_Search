package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
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

    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM Users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
        return count != null && count > 0;
    }

    public void updateUser(UserDto userDto) {
        String sql = "UPDATE Users SET name = ?, surname = ?, age = ?, phoneNumber = ? WHERE id = ?";
        jdbcTemplate.update(sql, userDto.getName(), userDto.getSurname(), userDto.getAge(), userDto.getPhoneNumber(), userDto.getId());
    }

    public Long createUser(UserDto userDto) {
        String sql = "INSERT INTO Users (name, surname, age, email, password, phoneNumber, avatar, ACCOUNTTYPE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userDto.getName());
            ps.setString(2, userDto.getSurname());
            ps.setByte(3, userDto.getAge());
            ps.setString(4, userDto.getEmail());
            ps.setString(5, userDto.getPassword());
            ps.setString(6, userDto.getPhoneNumber());
            ps.setString(7, userDto.getAvatar());
            ps.setString(8, userDto.getAccType());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
