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
                        .resumeId((int) rs.getLong("resumeId"))
                        .vacancyId((int) rs.getLong("vacancyId"))
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
