package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor

public class ImageDao {
    private final JdbcTemplate jdbcTemplate;


    public UserDto findUserByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        List<UserDto> users = jdbcTemplate.query(sql, new Object[]{email}, new BeanPropertyRowMapper<>(UserDto.class));
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    public void updateUser(UserDto userDto) {
        String sql = "UPDATE Users SET name = ?, surname = ?, age = ?, email = ?, phoneNumber = ?, avatar = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                userDto.getName(),
                userDto.getSurname(),
                userDto.getAge(),
                userDto.getEmail(),
                userDto.getPhoneNumber(),
                userDto.getAvatar(),
                userDto.getId()
        );
    }
}
