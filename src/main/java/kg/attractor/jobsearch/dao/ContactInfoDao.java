package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.ContactInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class ContactInfoDao {
    private final JdbcTemplate jdbcTemplate;

    public void saveContactInfo(ContactInfoDto contactInfoDto) {
        String sql = "INSERT INTO CONTACT_INFO (TYPE_ID, RESUME_ID, CONTACT_VALUE) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, contactInfoDto.getTypeId(), contactInfoDto.getResumeId(), contactInfoDto.getValue());
    }

    public void updateContactInfo(ContactInfoDto contactInfoDto) {
        String sql = "UPDATE CONTACT_INFO SET TYPE_ID = ?, CONTACT_VALUE = ? WHERE id = ?";
        jdbcTemplate.update(sql, contactInfoDto.getTypeId(), contactInfoDto.getValue(), contactInfoDto.getId());
    }
}
