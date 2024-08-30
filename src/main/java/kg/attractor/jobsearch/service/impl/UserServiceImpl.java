package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.customDto.UpdateUserProfileDto;
import kg.attractor.jobsearch.errors.CanNotFindUserException;
import kg.attractor.jobsearch.model.Authority;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.repository.AuthorityRepository;
import kg.attractor.jobsearch.repository.UserRepository;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserById(Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> {
                        log.warn("User with ID {} not found", userId);
                        return new CanNotFindUserException("User with ID " + userId + " not found");
                    });
            return mapToUserDto(user);
        } catch (Exception e) {
            log.error("An error occurred while fetching user with ID {}: {}", userId, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching user with ID " + userId);
        }
    }

    @Override
    public List<UserDto> getUsersByName(String name) {
        try {
            List<User> users = userRepository.findByName(name);
            return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching users by name {}: {}", name, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching users by name " + name);
        }
    }

    @Override
    public List<UserDto> getUsersByPhoneNumber(String phoneNumber) {
        try {
            List<User> users = userRepository.findByPhoneNumber(phoneNumber);
            return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching users by phone number {}: {}", phoneNumber, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching users by phone number " + phoneNumber);
        }
    }

    @Override
    public List<UserDto> getUsersByEmail(String email) {
        try {
            List<User> users = userRepository.findByEmail(email);
            return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("An error occurred while fetching users by email {}: {}", email, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching users by email " + email);
        }
    }

    @Override
    public UserDto getApplicantById(Long userId) {
        try {
            User applicant = userRepository.findById(userId)
                    .filter(user -> user.getAuthority().getId().equals(1L))
                    .orElseThrow(() -> {
                        log.warn("Applicant with ID {} not found", userId);
                        return new CanNotFindUserException("Applicant with ID " + userId + " not found");
                    });
            return mapToUserDto(applicant);
        } catch (Exception e) {
            log.error("An error occurred while fetching applicant with ID {}: {}", userId, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching applicant with ID " + userId);
        }
    }

    @Override
    public UserDto getEmployerById(Long userId) {
        try {
            User employer = userRepository.findById(userId)
                    .filter(user -> user.getAuthority().getId().equals(2L))
                    .orElseThrow(() -> {
                        log.warn("Employer with ID {} not found", userId);
                        return new CanNotFindUserException("Employer with ID " + userId + " not found");
                    });
            return mapToUserDto(employer);
        } catch (Exception e) {
            log.error("An error occurred while fetching employer with ID {}: {}", userId, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching employer with ID " + userId);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try {
            return userRepository.existsByEmail(email);
        } catch (Exception e) {
            log.error("An error occurred while checking existence of email {}: {}", email, e.getMessage());
            throw new CanNotFindUserException("An error occurred while checking existence of email " + email);
        }
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        try {
            if (userRepository.existsByEmail(userDto.getEmail())) {
                log.warn("User with email {} already exists", userDto.getEmail());
                throw new CanNotFindUserException("User with email " + userDto.getEmail() + " already exists");
            }

            Authority authority = authorityRepository.findById(userDto.getAuthorityId())
                    .orElseThrow(() -> new RuntimeException("Authority not found"));

            User user = mapToUser(userDto);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setEnabled(true);
            user.setAuthority(authority);

            return mapToUserDto(userRepository.save(user));
        } catch (CanNotFindUserException e) {
            throw e;
        } catch (Exception e) {
            log.error("An error occurred while registering user: {}", e.getMessage());
            throw new RuntimeException("An error occurred while registering user");
        }
    }


    @Override
    public UserDto updateUserProfile(UpdateUserProfileDto updateUserProfileDto) throws CanNotFindUserException {
        try {
            User existingUser = userRepository.findById(updateUserProfileDto.getId())
                    .orElseThrow(() -> {
                        log.warn("User with ID {} not found", updateUserProfileDto.getId());
                        return new CanNotFindUserException("User with ID " + updateUserProfileDto.getId() + " not found");
                    });

            existingUser.setName(updateUserProfileDto.getName());
            existingUser.setSurname(updateUserProfileDto.getSurname());
            existingUser.setAge(updateUserProfileDto.getAge());
            existingUser.setPhoneNumber(updateUserProfileDto.getPhoneNumber());

            return mapToUserDto(userRepository.save(existingUser));
        } catch (CanNotFindUserException e) {
            throw e;
        } catch (Exception e) {
            log.error("An error occurred while updating user profile with ID {}: {}", updateUserProfileDto.getId(), e.getMessage());
            throw new RuntimeException("An error occurred while updating user profile");
        }
    }

    @Override
    public UserDto getUserByEmail(String email) {
        try {
            List<User> users = userRepository.findByEmail(email);
            if (users.isEmpty()) {
                log.warn("User with email {} not found", email);
                throw new CanNotFindUserException("User with email " + email + " not found");
            }
            return mapToUserDto(users.getFirst());
        } catch (Exception e) {
            log.error("An error occurred while fetching user by email {}: {}", email, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching user by email " + email);
        }
    }

    private UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .enabled(user.isEnabled())
                .authorityId(user.getAuthority().getId())
                .build();
    }

    private User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .avatar(userDto.getAvatar())
                .enabled(userDto.getEnabled() != null ? userDto.getEnabled() : false)
                .build();
    }
}


