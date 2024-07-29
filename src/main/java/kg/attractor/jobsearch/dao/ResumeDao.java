package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.ResumeDto;
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

    public void saveResume(ResumeDto resumeDto) {
        String sql = "INSERT INTO Resume (APPLICANT_ID, name, CATEGORY_ID, salary, IS_ACTIVE, CREATED_DATE, UPDATE_TIME) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, resumeDto.getApplicantId(), resumeDto.getName(), resumeDto.getCategoryId(), resumeDto.getSalary(), resumeDto.isActive(), resumeDto.getCreatedDate(), resumeDto.getUpdateTime());
    }

    public List<ResumeDto> findAllResumes() {
        String sql = "SELECT * FROM Resume";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                ResumeDto.builder()
                        .id(rs.getLong("id"))
                        .applicantId(rs.getInt("applicant_id"))
                        .name(rs.getString("name"))
                        .categoryId(rs.getInt("category_id"))
                        .salary(rs.getDouble("salary"))
                        .isActive(rs.getBoolean("is_active"))
                        .createdDate(rs.getDate("created_date").toLocalDate())
                        .updateTime(rs.getDate("update_time").toLocalDate())
                        .build()
        );
    }

    public void updateResume(ResumeDto resumeDto) {
        String sql = "UPDATE Resume SET name = ?, CATEGORY_ID = ?, salary = ?, IS_ACTIVE = ?, CREATED_DATE = ?, UPDATE_TIME = ? WHERE id = ?";
        jdbcTemplate.update(sql, resumeDto.getName(), resumeDto.getCategoryId(), resumeDto.getSalary(), resumeDto.isActive(), resumeDto.getCreatedDate(), resumeDto.getUpdateTime(), resumeDto.getId());
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

    private ResumeDto mapRowToResumeDto(ResultSet rs) throws SQLException {
        return ResumeDto.builder()
                .id(rs.getLong("id"))
                .applicantId(rs.getInt("applicant_id"))
                .name(rs.getString("name"))
                .categoryId(rs.getInt("category_id"))
                .salary(rs.getDouble("salary"))
                .isActive(rs.getBoolean("is_active"))
                .createdDate(rs.getDate("created_date").toLocalDate())
                .updateTime(rs.getDate("update_time").toLocalDate())
                .build();
    }
}
