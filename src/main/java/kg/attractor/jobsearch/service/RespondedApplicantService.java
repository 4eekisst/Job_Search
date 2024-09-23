package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.model.RespondedApplicant;

import java.util.List;

public interface RespondedApplicantService {
    RespondedApplicant findRespondedApplicant(RespondedApplicantDto respondedApplicantDto);
    List<RespondedApplicantDto> getApplicantsForVacancy(Long vacancyId);
    List<VacancyDto> getVacanciesByApplicantId(Integer applicantId);
    void respondToVacancy(Long resumeId, String messageText, Long vacancyId);
    void respondToVacancy(RespondedApplicantDto respondedApplicantDto);
}
