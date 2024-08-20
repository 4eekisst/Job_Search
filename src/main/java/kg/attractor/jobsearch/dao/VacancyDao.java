package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor

public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public VacancyDto saveVacancy(VacancyDto vacancyDto) {
        String sql = "INSERT INTO Vacancy (name, description, CATEGORY_ID, AUTHOR_ID, salary, EXP_FROM, EXP_TO, IS_ACTIVE, CREATED_DATE, UPDATE_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vacancyDto.getName());
            ps.setString(2, vacancyDto.getDescription());
            ps.setLong(3, vacancyDto.getCategoryId());
            ps.setLong(4, vacancyDto.getAuthorId());
            ps.setDouble(5, vacancyDto.getSalary());
            ps.setInt(6, vacancyDto.getExpFrom());
            ps.setInt(7, vacancyDto.getExpTo());
            ps.setBoolean(8, vacancyDto.getIsActive());
            ps.setTimestamp(9, Timestamp.valueOf(vacancyDto.getCreatedDate()));
            ps.setTimestamp(10, Timestamp.valueOf(vacancyDto.getUpdateTime()));
            return ps;
        }, keyHolder);
        Long id = keyHolder.getKey().longValue();
        vacancyDto.setId(id);
        return vacancyDto;
    }

    public void updateVacancy(VacancyDto vacancyDto) {
        String sql = "UPDATE Vacancy SET name = ?, description = ?, CATEGORY_ID = ?, AUTHOR_ID = ?, salary = ?, EXP_FROM = ?, EXP_TO = ?, IS_ACTIVE = ?, UPDATE_TIME = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                vacancyDto.getName(),
                vacancyDto.getDescription(),
                vacancyDto.getCategoryId(),
                vacancyDto.getAuthorId(),
                vacancyDto.getSalary(),
                vacancyDto.getExpFrom(),
                vacancyDto.getExpTo(),
                vacancyDto.getIsActive(),
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

    public List<VacancyDto> findVacanciesByUserId(Long userId) {
        String sql = "SELECT * FROM Vacancy WHERE AUTHOR_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> mapRowToVacancyDto(rs));
    }
}
