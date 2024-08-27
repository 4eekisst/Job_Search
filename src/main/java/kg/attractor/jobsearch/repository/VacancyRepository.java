package kg.attractor.jobsearch.repository;

import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    Page<Vacancy> findAllByAuthorId(Long authorId, Pageable pageable);
    @Query("SELECT v FROM Vacancy v LEFT JOIN v.respondedApplicants ra GROUP BY v.id ORDER BY COUNT(ra) DESC")
    Page<Vacancy> findAllOrderByResponseCountDesc(Pageable pageable);
    Page<Vacancy> findAllByOrderByCreatedDateDesc(Pageable pageable);
}

