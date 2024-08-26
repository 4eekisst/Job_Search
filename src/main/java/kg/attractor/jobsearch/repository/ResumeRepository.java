package kg.attractor.jobsearch.repository;

import kg.attractor.jobsearch.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query("SELECT r FROM Resume r WHERE r.category.id = :categoryId")
    List<Resume> findByCategoryId(@Param("categoryId") Long categoryId);
    @Query("SELECT r FROM Resume r WHERE r.applicant.id = :applicantId")
    List<Resume> findByApplicantId(@Param("applicantId") Long applicantId);
    Page<Resume> findByApplicantId(Long applicantId, Pageable pageable);
}
