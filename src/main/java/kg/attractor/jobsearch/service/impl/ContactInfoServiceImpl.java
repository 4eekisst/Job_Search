package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.ContactInfoDao;
import kg.attractor.jobsearch.dto.ContactInfoDto;
import kg.attractor.jobsearch.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class ContactInfoServiceImpl implements ContactInfoService {
    private final ContactInfoDao contactInfoDao;

    @Override public void saveContactInfo(ContactInfoDto contactInfoDto) {contactInfoDao.saveContactInfo(contactInfoDto);}
}
