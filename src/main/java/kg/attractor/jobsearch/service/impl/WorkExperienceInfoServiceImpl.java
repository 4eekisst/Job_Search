package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.WorkExperienceInfoDao;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class WorkExperienceInfoServiceImpl implements WorkExperienceInfoService {
    private final WorkExperienceInfoDao workExperienceInfoDao;

    @Override
    public void saveWorkExperienceInfo(WorkExperienceInfoDto workExperienceInfoDto) {workExperienceInfoDao.saveWorkExperienceInfo(workExperienceInfoDto);}
}
