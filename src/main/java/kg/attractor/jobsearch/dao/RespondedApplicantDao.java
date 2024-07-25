package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor


public class RespondedApplicantDao {
    private final JdbcTemplate jdbcTemplate;

    public void saveRespondedApplicant(RespondedApplicantDto respondedApplicantDto) {
        String sql = "INSERT INTO RespondedApplicant (resumeId, vacancyId, confirmation) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, respondedApplicantDto.getResumeId(), respondedApplicantDto.getVacancyId(), respondedApplicantDto.getConfirmation());
    }

    public List<RespondedApplicantDto> findApplicantsForVacancy(Long vacancyId) {
        String sql = "SELECT * FROM RespondedApplicant WHERE vacancyId = ?";
        return jdbcTemplate.query(sql, new Object[]{vacancyId}, (rs, rowNum) ->
                RespondedApplicantDto.builder()
                        .id(rs.getLong("id"))
                        .resumeId((int) rs.getLong("resumeId"))
                        .vacancyId((int) rs.getLong("vacancyId"))
                        .confirmation(rs.getBoolean("confirmation"))
                        .build()
        );
    }
}
