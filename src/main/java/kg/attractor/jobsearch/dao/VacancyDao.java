package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.VacancyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
@RequiredArgsConstructor

public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public void saveVacancy(VacancyDto vacancyDto) {
        String sql = "INSERT INTO Vacancy (name, description, categoryId, authorId, salary, expFrom, expTo, isActive, createdDate, updateTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                vacancyDto.getName(),
                vacancyDto.getDescription(),
                vacancyDto.getCategoryId(),
                vacancyDto.getAuthorId(),
                vacancyDto.getSalary(),
                vacancyDto.getExpFrom(),
                vacancyDto.getExpTo(),
                vacancyDto.getIsActive(),
                Timestamp.valueOf(vacancyDto.getCreatedDate()),
                Timestamp.valueOf(vacancyDto.getUpdateTime())
        );
    }

    public void updateVacancy(VacancyDto vacancyDto) {
        String sql = "UPDATE Vacancy SET name = ?, description = ?, categoryId = ?, authorId = ?, salary = ?, expFrom = ?, expTo = ?, isActive = ?, createdDate = ?, updateTime = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                vacancyDto.getName(),
                vacancyDto.getDescription(),
                vacancyDto.getCategoryId(),
                vacancyDto.getAuthorId(),
                vacancyDto.getSalary(),
                vacancyDto.getExpFrom(),
                vacancyDto.getExpTo(),
                vacancyDto.getIsActive(),
                Timestamp.valueOf(vacancyDto.getCreatedDate()),
                Timestamp.valueOf(vacancyDto.getUpdateTime()),
                vacancyDto.getId()
        );
    }

    public void deleteVacancy(Long id) {
        String sql = "DELETE FROM Vacancy WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<VacancyDto> findActiveVacancies() {
        String sql = "SELECT * FROM Vacancy WHERE isActive = TRUE";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToVacancyDto(rs));
    }

    public List<VacancyDto> findVacanciesByCategory(Long categoryId) {
        String sql = "SELECT * FROM Vacancy WHERE categoryId = ?";
        return jdbcTemplate.query(sql, new Object[]{categoryId}, (rs, rowNum) -> mapRowToVacancyDto(rs));
    }

    private VacancyDto mapRowToVacancyDto(ResultSet rs) throws SQLException {
        return VacancyDto.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .categoryId(rs.getLong("categoryId"))
                .authorId(rs.getLong("authorId"))
                .salary(rs.getDouble("salary"))
                .expFrom(rs.getInt("expFrom"))
                .expTo(rs.getInt("expTo"))
                .isActive(rs.getBoolean("isActive"))
                .createdDate(rs.getTimestamp("createdDate").toLocalDateTime())
                .updateTime(rs.getTimestamp("updateTime").toLocalDateTime())
                .build();
    }
}
