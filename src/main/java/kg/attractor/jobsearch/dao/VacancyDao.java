package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.VacancyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor

public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public void saveVacancy(VacancyDto vacancyDto) {
        String sql = "INSERT INTO Vacancy (name, description, CATEGORY_ID, AUTHOR_ID, salary, EXP_FROM, EXP_TO, IS_ACTIVE, CREATED_DATE, UPDATE_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        String sql = "UPDATE Vacancy SET name = ?, description = ?, CATEGORY_ID = ?, AUTHOR_ID = ?, salary = ?, EXP_FROM = ?, EXP_TO = ?, IS_ACTIVE = ?, CREATED_DATE = ?, UPDATE_TIME = ? WHERE id = ?";
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
        String sql = "SELECT * FROM Vacancy WHERE IS_ACTIVE = TRUE";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToVacancyDto(rs));
    }

    public List<VacancyDto> findVacanciesByCategory(Long categoryId) {
        String sql = "SELECT * FROM Vacancy WHERE CATEGORY_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{categoryId}, (rs, rowNum) -> mapRowToVacancyDto(rs));
    }

    public List<VacancyDto> findAllVacancies() {
        String sql = "SELECT * FROM Vacancy";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToVacancyDto(rs));
    }

    public VacancyDto findVacancyById(Long id) {
        String sql = "SELECT * FROM Vacancy WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> mapRowToVacancyDto(rs));
    }

    private VacancyDto mapRowToVacancyDto(ResultSet rs) throws SQLException {
        return VacancyDto.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .categoryId(rs.getLong("CATEGORY_ID"))
                .authorId(rs.getLong("AUTHOR_ID"))
                .salary(rs.getDouble("salary"))
                .expFrom(rs.getInt("EXP_FROM"))
                .expTo(rs.getInt("EXP_TO"))
                .isActive(rs.getBoolean("IS_ACTIVE"))
                .createdDate(rs.getObject("CREATED_DATE", LocalDateTime.class))
                .updateTime(rs.getObject("UPDATE_TIME", LocalDateTime.class))
                .build();
    }
}
