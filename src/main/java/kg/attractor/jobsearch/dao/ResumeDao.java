package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.ResumeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
@RequiredArgsConstructor

public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

    public int saveResume(ResumeDto resume) {
        String sql = "INSERT INTO Resume (APPLICANT_ID, name, CATEGORY_ID, salary, IS_ACTIVE, CREATED_DATE, UPDATE_TIME) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, resume.getApplicantId());
            ps.setString(2, resume.getName());
            ps.setInt(3, resume.getCategoryId());
            ps.setDouble(4, resume.getSalary());
            ps.setBoolean(5, resume.getIsActive());
            ps.setTimestamp(6, Timestamp.valueOf(resume.getCreatedDate()));
            ps.setTimestamp(7, Timestamp.valueOf(resume.getUpdateTime()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        return key != null ? key.intValue() : -1;

    }


    public List<ResumeDto> findAllResumes() {
        String sql = "SELECT r.id, r.applicant_id, r.name, r.category_id, r.salary, r.is_active, r.created_date, r.update_time, u.email " +
                "FROM Resume r " +
                "JOIN USERS u ON r.applicant_id = u.id";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                ResumeDto.builder()
                        .id(rs.getLong("id"))
                        .applicantId(rs.getInt("applicant_id"))
                        .name(rs.getString("name"))
                        .categoryId(rs.getInt("category_id"))
                        .salary(rs.getDouble("salary"))
                        .isActive(rs.getBoolean("is_active"))
                        .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                        .updateTime(rs.getTimestamp("update_time").toLocalDateTime())
                        .email(rs.getString("email"))
                        .build()
        );
    }

    public void updateResume(ResumeDto resumeDto) {
        String sql = "UPDATE Resume SET name = ?, CATEGORY_ID = ?, salary = ?, IS_ACTIVE = ?, CREATED_DATE = ?, UPDATE_TIME = ? WHERE id = ?";
        jdbcTemplate.update(sql, resumeDto.getName(), resumeDto.getCategoryId(), resumeDto.getSalary(), resumeDto.getIsActive(), resumeDto.getCreatedDate(), resumeDto.getUpdateTime(), resumeDto.getId());
    }

    public void deleteResume(Long id) {
        String sql = "DELETE FROM Resume WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<ResumeDto> findResumesByCategory(Long categoryId) {
        String sql = "SELECT * FROM Resume WHERE CATEGORY_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{categoryId}, (rs, rowNum) -> mapRowToResumeDto(rs));
    }

    public List<ResumeDto> findResumesByApplicantId(Integer applicantId) {
        String sql = "SELECT * FROM Resume WHERE APPLICANT_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{applicantId}, (rs, rowNum) -> mapRowToResumeDto(rs));
    }

    public ResumeDto findResumeById(Long id) {
        String sql = "SELECT r.id, r.applicant_id, r.name, r.category_id, r.salary, r.is_active, r.created_date, r.update_time, u.email " +
                "FROM Resume r " +
                "JOIN USERS u ON r.applicant_id = u.id " +
                "WHERE r.id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                ResumeDto.builder()
                        .id(rs.getLong("id"))
                        .applicantId(rs.getInt("applicant_id"))
                        .name(rs.getString("name"))
                        .categoryId(rs.getInt("category_id"))
                        .salary(rs.getDouble("salary"))
                        .isActive(rs.getBoolean("is_active"))
                        .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                        .updateTime(rs.getTimestamp("update_time").toLocalDateTime())
                        .email(rs.getString("email"))
                        .build()
        );
    }


    public List<ResumeDto> findResumesByUserId(Long userId) {
        String sql = "SELECT r.id, r.applicant_id, r.name, r.category_id, r.salary, r.is_active, r.created_date, r.update_time, u.email " +
                "FROM Resume r " +
                "JOIN USERS u ON r.applicant_id = u.id " +
                "WHERE r.applicant_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) ->
                ResumeDto.builder()
                        .id(rs.getLong("id"))
                        .applicantId(rs.getInt("applicant_id"))
                        .name(rs.getString("name"))
                        .categoryId(rs.getInt("category_id"))
                        .salary(rs.getDouble("salary"))
                        .isActive(rs.getBoolean("is_active"))
                        .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                        .updateTime(rs.getTimestamp("update_time").toLocalDateTime())
                        .email(rs.getString("email"))
                        .build()
        );
    }

    private ResumeDto mapRowToResumeDto(ResultSet rs) throws SQLException {
        return ResumeDto.builder()
                .id(rs.getLong("id"))
                .applicantId(rs.getInt("applicant_id"))
                .name(rs.getString("name"))
                .categoryId(rs.getInt("category_id"))
                .salary(rs.getDouble("salary"))
                .isActive(rs.getBoolean("is_active"))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .updateTime(rs.getTimestamp("update_time").toLocalDateTime())
                .build();
    }
}
