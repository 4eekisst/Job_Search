package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.errors.CanNotFindRespondedApplicantException;
import kg.attractor.jobsearch.model.Message;
import kg.attractor.jobsearch.model.RespondedApplicant;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.repository.RespondedApplicantRepository;
import kg.attractor.jobsearch.repository.ResumeRepository;
import kg.attractor.jobsearch.repository.VacancyRepository;
import kg.attractor.jobsearch.service.MessageService;
import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final RespondedApplicantRepository respondedApplicantRepository;
    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;
    private final MessageService messageService;

    @Override
    public void respondToVacancy(Long resumeId, String messageText, Long vacancyId) {
        try {
            RespondedApplicantDto respondedApplicantDto = RespondedApplicantDto.builder()
                    .resumeId(Math.toIntExact(resumeId))
                    .vacancyId(Math.toIntExact(vacancyId))
                    .confirmation(true)
                    .build();

            RespondedApplicant respondedApplicant = mapToEntity(respondedApplicantDto);
            respondedApplicantRepository.save(respondedApplicant);

            Message msg = Message.builder()
                    .text(messageText)
                    .timeStamp(LocalDate.now())
                    .respondedApplicant(respondedApplicant)
                    .build();
            messageService.saveMessage(msg);
        } catch (Exception e) {
            log.error("An error occurred while responding to vacancy and sending message: {}", e.getMessage());
            throw new CanNotFindRespondedApplicantException("Unable to respond to vacancy or send message");
        }
    }

    @Override
    public void respondToVacancy(RespondedApplicantDto respondedApplicantDto) {}

    @Override
    public RespondedApplicant findRespondedApplicant(RespondedApplicantDto respondedApplicantDto) {
        return respondedApplicantRepository.findByResumeIdAndVacancyId(
                respondedApplicantDto.getResumeId(),
                respondedApplicantDto.getVacancyId()
        ).orElseThrow(() -> new RuntimeException("Responded Applicant not found"));
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

    @Override
    public List<RespondedApplicantDto> getRespondedApplicantsByUserId(Long userId) {
        try {
            List<RespondedApplicant> respondedApplicants = respondedApplicantRepository.findAll()
                    .stream()
                    .filter(applicant -> applicant.getResume().getApplicant().getId().equals(userId))
                    .collect(Collectors.toList());

            return respondedApplicants.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching responded applicants for user ID {}: {}", userId, e.getMessage());

            return Collections.emptyList();
        }
    }

    @Override
    public int getResponseCountByUserId(Long userId) {
        try {
            return (int) respondedApplicantRepository.findAll()
                    .stream()
                    .filter(applicant -> applicant.getResume().getApplicant().getId().equals(userId))
                    .count();
        } catch (Exception e) {
            log.error("An error occurred while counting responses for user ID {}: {}", userId, e.getMessage());
            throw new CanNotFindRespondedApplicantException("Unable to count responses for user ID " + userId);
        }
    }

    @Override
    public boolean hasUserRespondedToVacancy(Long userId, Long vacancyId) {
        try {
            return respondedApplicantRepository.findAll()
                    .stream()
                    .anyMatch(applicant ->
                            applicant.getResume().getApplicant().getId().equals(userId) &&
                                    applicant.getVacancy().getId().equals(vacancyId)
                    );
        } catch (Exception e) {
            log.error("An error occurred while checking if user {} responded to vacancy {}: {}", userId, vacancyId, e.getMessage());
            throw new CanNotFindRespondedApplicantException("Unable to check response for user ID " + userId + " and vacancy ID " + vacancyId);
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
