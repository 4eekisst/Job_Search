package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.RespondedApplicantDao;
import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.errors.CanNotFindRespondedApplicantException;
import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class RespondedApplicantServiceImpl implements RespondedApplicantService {

    private final RespondedApplicantDao respondedApplicantDao;

    @Override
    public void respondToVacancy(RespondedApplicantDto respondedApplicantDto) {
        try {
            respondedApplicantDao.saveRespondedApplicant(respondedApplicantDto);
        } catch (Exception e) {
            log.error("An error occurred while responding to vacancy: {}", e.getMessage());
            throw new CanNotFindRespondedApplicantException("Unable to respond to vacancy");
        }
    }

    @Override
    public List<RespondedApplicantDto> getApplicantsForVacancy(Long vacancyId) {
        try {
            List<RespondedApplicantDto> applicants = respondedApplicantDao.findApplicantsForVacancy(vacancyId);
            if (applicants.isEmpty()) {
                throw new CanNotFindRespondedApplicantException("No applicants found for vacancy ID " + vacancyId);
            }
            return applicants;
        } catch (Exception e) {
            log.error("An error occurred while fetching applicants for vacancy ID {}: {}", vacancyId, e.getMessage());
            throw new CanNotFindRespondedApplicantException("Unable to fetch applicants for vacancy ID " + vacancyId);
        }
    }


    @Override
    public List<VacancyDto> getVacanciesByApplicantId(Integer applicantId) {
        try {
            return respondedApplicantDao.findVacanciesByApplicantId(applicantId);
        } catch (Exception e) {
            log.error("An error occurred while fetching vacancies for applicant ID {}: {}", applicantId, e.getMessage());
            throw new CanNotFindRespondedApplicantException("Unable to fetch vacancies for applicant ID " + applicantId);
        }
    }
}
