package kg.attractor.jobsearch.repository;

import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    Page<Vacancy> findAllByAuthorId(Long authorId, Pageable pageable);
}

