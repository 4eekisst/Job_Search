package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.RespondedApplicantDao;
import kg.attractor.jobsearch.dto.RespondedApplicantDto;
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
        respondedApplicantDao.saveRespondedApplicant(respondedApplicantDto);
    }
    @Override
    public List<RespondedApplicantDto> getApplicantsForVacancy(Long vacancyId) {
        return respondedApplicantDao.findApplicantsForVacancy(vacancyId);
    }
}
