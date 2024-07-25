package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long userId);
    List<UserDto> getUsersByName(String name);
    List<UserDto> getUsersByPhoneNumber(String phoneNumber);
    List<UserDto> getUsersByEmail(String email);
    UserDto getApplicantById(Long userId);
    UserDto getEmployerById(Long userId);
    boolean existsByEmail(String email);
}
