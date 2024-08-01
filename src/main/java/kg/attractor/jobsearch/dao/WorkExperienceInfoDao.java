package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class WorkExperienceInfoDao {
    private final JdbcTemplate jdbcTemplate;

    public void saveWorkExperienceInfo(WorkExperienceInfoDto workExperienceInfoDto) {
        String sql = "INSERT INTO WORK_EXPERIENCE_INFO (RESUME_ID, years, COMPANY_NAME, position, responsibilities) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, workExperienceInfoDto.getResumeId(), workExperienceInfoDto.getYears(), workExperienceInfoDto.getCompanyName(), workExperienceInfoDto.getPosition(), workExperienceInfoDto.getResponsibilities());
    }
}
