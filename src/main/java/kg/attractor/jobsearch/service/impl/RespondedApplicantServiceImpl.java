package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.errors.CanNotFindRespondedApplicantException;
import kg.attractor.jobsearch.model.RespondedApplicant;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.repository.RespondedApplicantRepository;
import kg.attractor.jobsearch.repository.ResumeRepository;
import kg.attractor.jobsearch.repository.VacancyRepository;
import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final RespondedApplicantRepository respondedApplicantRepository;
    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;
    @Override
    public void respondToVacancy(RespondedApplicantDto respondedApplicantDto) {
        try {
            RespondedApplicant respondedApplicant = mapToEntity(respondedApplicantDto);
            respondedApplicantRepository.save(respondedApplicant);
        } catch (Exception e) {
            log.error("An error occurred while responding to vacancy: {}", e.getMessage());
            throw new CanNotFindRespondedApplicantException("Unable to respond to vacancy");
        }
    }

    @Override
    public List<RespondedApplicantDto> getApplicantsForVacancy(Long vacancyId) {
        try {
            List<RespondedApplicant> applicants = respondedApplicantRepository.findAll()
                    .stream()
                    .filter(applicant -> applicant.getVacancy().getId().equals(vacancyId))
                    .toList();

            if (applicants.isEmpty()) {
                throw new CanNotFindRespondedApplicantException("No applicants found for vacancy ID " + vacancyId);
            }

            return applicants.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching applicants for vacancy ID {}: {}", vacancyId, e.getMessage());
            throw new CanNotFindRespondedApplicantException("Unable to fetch applicants for vacancy ID " + vacancyId);
        }
    }

    @Override
    public List<VacancyDto> getVacanciesByApplicantId(Integer applicantId) {
        try {
            List<RespondedApplicant> respondedApplicants = respondedApplicantRepository.findAll()
                    .stream()
                    .filter(applicant -> applicant.getResume().getId().equals(applicantId.longValue()))
                    .toList();

            return respondedApplicants.stream()
                    .map(applicant -> mapToVacancyDto(applicant.getVacancy()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching vacancies for applicant ID {}: {}", applicantId, e.getMessage());
            throw new CanNotFindRespondedApplicantException("Unable to fetch vacancies for applicant ID " + applicantId);
        }
    }

    private RespondedApplicant mapToEntity(RespondedApplicantDto dto) {
        return RespondedApplicant.builder()
                .id(dto.getId())
                .resume(resumeRepository.findById(dto.getResumeId().longValue())
                        .orElseThrow(() -> new CanNotFindRespondedApplicantException("Resume not found")))
                .vacancy(vacancyRepository.findById(dto.getVacancyId().longValue())
                        .orElseThrow(() -> new CanNotFindRespondedApplicantException("Vacancy not found")))
                .confirmation(dto.getConfirmation())
                .build();
    }

    private RespondedApplicantDto mapToDto(RespondedApplicant model) {
        return RespondedApplicantDto.builder()
                .id(model.getId())
                .resumeId(Math.toIntExact(model.getResume().getId()))
                .vacancyId(Math.toIntExact(model.getVacancy().getId()))
                .confirmation(model.getConfirmation())
                .build();
    }

    private VacancyDto mapToVacancyDto(Vacancy vacancy) {
        return VacancyDto.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryId(vacancy.getCategory().getId())
                .authorId(vacancy.getAuthor().getId())
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .isActive(vacancy.getIsActive())
                .createdDate(vacancy.getCreatedDate() != null ? vacancy.getCreatedDate().atStartOfDay() : null)
                .updateTime(vacancy.getUpdateTime() != null ? vacancy.getUpdateTime().atStartOfDay() : null)
                .build();
    }
}
