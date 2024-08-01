package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.EducationInfoDao;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class EducationInfoServiceImpl implements EducationInfoService {
    private final EducationInfoDao educationInfoDao;

    @Override
    public void saveEducationInfo(EducationInfoDto educationInfoDto) {educationInfoDao.saveEducationInfo(educationInfoDto);}

    @Override
    public void updateEducationInfo(EducationInfoDto educationInfoDto) {educationInfoDao.updateEducationInfo(educationInfoDto);}
}
