package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.customDto.UpdateUserProfileDto;
import kg.attractor.jobsearch.errors.CanNotFindUserException;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long userId);
    List<UserDto> getUsersByName(String name);
    List<UserDto> getUsersByPhoneNumber(String phoneNumber);
    List<UserDto> getUsersByEmail(String email);
    UserDto getApplicantById(Long userId);
    UserDto getEmployerById(Long userId);
    boolean existsByEmail(String email);
    UserDto registerUser(UserDto userDto);
    UserDto updateUserProfile(UpdateUserProfileDto updateUserProfileDto) throws CanNotFindUserException;
}
