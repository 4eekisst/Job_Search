package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor

public class RespondedApplicantDao {
    private final JdbcTemplate jdbcTemplate;

    public void saveRespondedApplicant(RespondedApplicantDto respondedApplicantDto) {
        String sql = "INSERT INTO RESPONDED_APPLICANT (RESUME_ID, VACANCY_ID, confirmation) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, respondedApplicantDto.getResumeId(), respondedApplicantDto.getVacancyId(), respondedApplicantDto.getConfirmation());
    }

    public List<RespondedApplicantDto> findApplicantsForVacancy(Long vacancyId) {
        String sql = "SELECT * FROM RESPONDED_APPLICANT WHERE VACANCY_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{vacancyId}, (rs, rowNum) ->
                RespondedApplicantDto.builder()
                        .id(rs.getLong("id"))
                        .resumeId((int) rs.getLong("resume_id"))
                        .vacancyId((int) rs.getLong("vacancy_id"))
                        .confirmation(rs.getBoolean("confirmation"))
                        .build()
        );
    }

    public List<VacancyDto> findVacanciesByApplicantId(Integer applicantId) {
        String sql = "SELECT v.* " +
                "FROM Vacancy v " +
                "JOIN RESPONDED_APPLICANT ra ON v.id = ra.VACANCY_ID " +
                "WHERE ra.RESUME_ID IN (SELECT id FROM Resume WHERE APPLICANT_ID = ?)";
        return jdbcTemplate.query(sql, new Object[]{applicantId}, (rs, rowNum) -> mapRowToVacancyDto(rs));
    }


    private VacancyDto mapRowToVacancyDto(ResultSet rs) throws SQLException {
        return VacancyDto.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .categoryId(rs.getLong("category_id"))
                .authorId(rs.getLong("author_id"))
                .salary(rs.getDouble("salary"))
                .expFrom(rs.getInt("exp_from"))
                .expTo(rs.getInt("exp_to"))
                .isActive(rs.getBoolean("is_active"))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .updateTime(rs.getTimestamp("update_time").toLocalDateTime())
                .build();
    }
}
