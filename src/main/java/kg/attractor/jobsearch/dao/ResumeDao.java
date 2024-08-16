package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor

public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

    public int saveResume(ResumeDto resume) {
        String sql = "INSERT INTO Resume (APPLICANT_ID, name, CATEGORY_ID, salary, IS_ACTIVE, CREATED_DATE, UPDATE_TIME) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, resume.getApplicantId(), resume.getName(), resume.getCategoryId(), resume.getSalary(), resume.getIsActive(), resume.getCreatedDate(), resume.getUpdateTime());
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
                        .createdDate(rs.getDate("created_date").toString())
                        .updateTime(rs.getDate("update_time").toString())
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
                        .createdDate(rs.getDate("created_date").toString())
                        .updateTime(rs.getDate("update_time").toString())
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
                .createdDate(rs.getDate("created_date").toString())
                .updateTime(rs.getDate("update_time").toString())
                .build();
    }
}
