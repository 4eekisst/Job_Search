package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.ContactTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor

public class ContactTypeDao {
    private final JdbcTemplate jdbcTemplate;

    public List<ContactTypeDto> getAllContactTypes() {
        String sql = "SELECT id, type FROM CONTACT_TYPE";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToContactTypeDto(rs));
    }

    private ContactTypeDto mapRowToContactTypeDto(ResultSet rs) throws SQLException {
        return ContactTypeDto.builder()
                .id(rs.getInt("id"))
                .type(rs.getString("type"))
                .build();
    }
}
