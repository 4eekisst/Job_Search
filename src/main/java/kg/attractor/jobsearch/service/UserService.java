package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.customDto.UpdateUserProfileDto;
import kg.attractor.jobsearch.errors.CanNotFindUserException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

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
    UserDto getUserByEmail(String email);
}
