package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.ContactTypeDto;
import kg.attractor.jobsearch.model.ContactType;
import kg.attractor.jobsearch.repository.ContactTypeRepository;
import kg.attractor.jobsearch.service.ContactTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class ContactTypeServiceImpl implements ContactTypeService {
    private final ContactTypeRepository contactTypeRepository;

    @Override
    public List<ContactTypeDto> getAllContactTypes() {
        List<ContactType> contactTypes = contactTypeRepository.findAll();
        return contactTypes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ContactTypeDto mapToDto(ContactType contactType) {
        return ContactTypeDto.builder()
                .id(Math.toIntExact(contactType.getId()))
                .type(contactType.getType())
                .build();
    }
}
