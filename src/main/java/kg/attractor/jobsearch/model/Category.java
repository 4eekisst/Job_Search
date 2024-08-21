package kg.attractor.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Resume> resumes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Vacancy> vacancies;
}
