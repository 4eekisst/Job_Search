package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor


public class RespondedApplicantDao {
    private final JdbcTemplate jdbcTemplate;

    public void saveRespondedApplicant(RespondedApplicantDto respondedApplicantDto) {
        String sql = "INSERT INTO RespondedApplicant (resumeId, vacancyId, confirmation) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, respondedApplicantDto.getResumeId(), respondedApplicantDto.getVacancyId(), respondedApplicantDto.getConfirmation());
    }
}
