package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.ResumeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor

public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

    public void saveResume(ResumeDto resumeDto) {
        String sql = "INSERT INTO Resume (applicantId, name, categoryId, salary, isActive, createdDate, updateTime) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, resumeDto.getApplicantId(), resumeDto.getName(), resumeDto.getCategoryId(), resumeDto.getSalary(), resumeDto.isActive(), resumeDto.getCreatedDate(), resumeDto.getUpdateTime());
    }

    public List<ResumeDto> findAllResumes() {
        String sql = "SELECT * FROM Resume";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                ResumeDto.builder()
                        .id(rs.getLong("id"))
                        .applicantId(rs.getInt("applicantId"))
                        .name(rs.getString("name"))
                        .categoryId(rs.getInt("categoryId"))
                        .salary(rs.getDouble("salary"))
                        .isActive(rs.getBoolean("isActive"))
                        .createdDate(rs.getDate("createdDate").toLocalDate())
                        .updateTime(rs.getDate("updateTime").toLocalDate())
                        .build()
        );
    }

    public List<ResumeDto> findResumesByCategory(int categoryId) {
        String sql = "SELECT * FROM Resume WHERE categoryId = ?";
        return jdbcTemplate.query(sql, new Object[]{categoryId}, (rs, rowNum) ->
                ResumeDto.builder()
                        .id(rs.getLong("id"))
                        .applicantId(rs.getInt("applicantId"))
                        .name(rs.getString("name"))
                        .categoryId(rs.getInt("categoryId"))
                        .salary(rs.getDouble("salary"))
                        .isActive(rs.getBoolean("isActive"))
                        .createdDate(rs.getDate("createdDate").toLocalDate())
                        .updateTime(rs.getDate("updateTime").toLocalDate())
                        .build()
        );
    }

    public void updateResume(ResumeDto resumeDto) {
        String sql = "UPDATE Resume SET name = ?, categoryId = ?, salary = ?, isActive = ?, createdDate = ?, updateTime = ? WHERE id = ?";
        jdbcTemplate.update(sql, resumeDto.getName(), resumeDto.getCategoryId(), resumeDto.getSalary(), resumeDto.isActive(), resumeDto.getCreatedDate(), resumeDto.getUpdateTime(), resumeDto.getId());
    }

    public void deleteResume(Long id) {
        String sql = "DELETE FROM Resume WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
