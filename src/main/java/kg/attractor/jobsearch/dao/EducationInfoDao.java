package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.EducationInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor

public class EducationInfoDao {

    private final JdbcTemplate jdbcTemplate;

    public void saveEducationInfo(EducationInfoDto educationInfoDto) {
        String sql = "INSERT INTO EDUCATION_INFO (RESUME_ID, institution, program, START_DATE, END_DATE, degree) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, educationInfoDto.getResumeId(), educationInfoDto.getInstitution(), educationInfoDto.getProgram(), educationInfoDto.getStartDate(), educationInfoDto.getEndDate(), educationInfoDto.getDegree());
    }
}
