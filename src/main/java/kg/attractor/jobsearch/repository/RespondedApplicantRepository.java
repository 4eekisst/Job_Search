package kg.attractor.jobsearch.repository;

import kg.attractor.jobsearch.model.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Long> {
    Optional<RespondedApplicant> findByResumeIdAndVacancyId(Integer resumeId, Integer vacancyId);
}
