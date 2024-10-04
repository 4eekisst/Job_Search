package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.ContactInfoDto;
import kg.attractor.jobsearch.errors.CanNotFindResumeException;
import kg.attractor.jobsearch.model.ContactInfo;
import kg.attractor.jobsearch.repository.ContactInfoRepository;
import kg.attractor.jobsearch.repository.ContactTypeRepository;
import kg.attractor.jobsearch.repository.ResumeRepository;
import kg.attractor.jobsearch.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class ContactInfoServiceImpl implements ContactInfoService {
    private final ContactInfoRepository contactInfoRepository;
    private final ResumeRepository resumeRepository;
    private final ContactTypeRepository contactTypeRepository;

    @Override
    public void saveContactInfo(ContactInfoDto contactInfoDto) {
        ContactInfo contactInfo = mapToContactInfo(contactInfoDto);
        contactInfoRepository.save(contactInfo);
    }

    @Override
    public void updateContactInfo(ContactInfoDto contactInfoDto) {
        ContactInfo contactInfo = mapToContactInfo(contactInfoDto);
        contactInfoRepository.save(contactInfo);
    }

    @Override
    public List<ContactInfoDto> getContactsByResumeId(Long resumeId) {
        List<ContactInfo> contacts = contactInfoRepository.findByResumeId(resumeId);
        return contacts.stream()
                .map(this::mapToContactInfoDto)
                .collect(Collectors.toList());
    }

    private ContactInfoDto mapToContactInfoDto(ContactInfo info) {
        return ContactInfoDto.builder()
                .id(info.getId())
                .resumeId(info.getResume().getId())
                .typeId(Math.toIntExact(info.getContactType().getId()))
                .value(info.getValue())
                .build();
    }

    private ContactInfo mapToContactInfo(ContactInfoDto dto) {
        return ContactInfo.builder()
                .id(dto.getId())
                .contactType(contactTypeRepository.findById(Long.valueOf(dto.getTypeId()))
                        .orElseThrow(() -> new IllegalArgumentException("Contact Type not found")))
                .resume(resumeRepository.findById(Long.valueOf(dto.getResumeId()))
                        .orElseThrow(() -> new CanNotFindResumeException("Resume not found")))
                .value(dto.getValue())
                .build();
    }
}
