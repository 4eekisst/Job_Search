package kg.attractor.jobsearch.model;

import lombok.Data;

@Data

public class User {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private Boolean enabled;
    private Long authority_id;
}
