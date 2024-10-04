package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ContactInfoDto;

import java.util.List;

public interface ContactInfoService {
    void saveContactInfo(ContactInfoDto contactInfoDto);

    void updateContactInfo(ContactInfoDto contactInfoDto);

    List<ContactInfoDto> getContactsByResumeId(Long resumeId);
}
