package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;

public interface RespondedApplicantService {
    void respondToVacancy(RespondedApplicantDto respondedApplicant);
}
