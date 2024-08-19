package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.ContactTypeDao;
import kg.attractor.jobsearch.dto.ContactTypeDto;
import kg.attractor.jobsearch.service.ContactTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor

public class ContactTypeServiceImpl implements ContactTypeService {
    private final ContactTypeDao contactTypeDao;

    @Override
    public List<ContactTypeDto> getAllContactTypes() {
        return contactTypeDao.getAllContactTypes();
    }
}
