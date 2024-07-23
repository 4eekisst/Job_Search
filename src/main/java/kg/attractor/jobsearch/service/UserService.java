package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ApplicantDto;
import kg.attractor.jobsearch.dto.UserDto;
public interface UserService {

    UserDto getApplicantById(Long userId);

    UserDto getEmployerById(Long userId);

    ApplicantDto applyToVacancy(ApplicantDto applicationDto);
}
