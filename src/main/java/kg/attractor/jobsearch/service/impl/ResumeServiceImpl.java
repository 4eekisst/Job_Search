package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;

    @Override
    public void createResume(ResumeDto resumeDto) {
        resumeDao.saveResume(resumeDto);
    }

    @Override
    public List<ResumeDto> getAllResumes() {
        return resumeDao.findAllResumes();
    }

    @Override
    public List<ResumeDto> getResumesByCategory(int categoryId) {
        return resumeDao.findResumesByCategory(categoryId);
    }

    @Override
    public void updateResume(ResumeDto resumeDto) {
        resumeDao.updateResume(resumeDto);
    }

    @Override
    public void deleteResume(Long id) {
        resumeDao.deleteResume(id);
    }
}
