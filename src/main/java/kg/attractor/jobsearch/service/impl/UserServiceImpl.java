package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.customDto.UpdateUserProfileDto;
import kg.attractor.jobsearch.errors.CanNotFindUserException;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserById(Long userId) {
        try {
            UserDto user = userDao.findUserById(userId);
            if (user == null) {
                log.warn("User with ID {} not found", userId);
                throw new CanNotFindUserException("User with ID " + userId + " not found");
            }
            return user;
        } catch (Exception e) {
            log.error("An error occurred while fetching user with ID {}: {}", userId, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching user with ID " + userId);
        }
    }

    @Override
    public List<UserDto> getUsersByName(String name) {
        try {
            return userDao.findUsersByName(name);
        } catch (Exception e) {
            log.error("An error occurred while fetching users by name {}: {}", name, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching users by name " + name);
        }
    }

    @Override
    public List<UserDto> getUsersByPhoneNumber(String phoneNumber) {
        try {
            return userDao.findUsersByPhoneNumber(phoneNumber);
        } catch (Exception e) {
            log.error("An error occurred while fetching users by phone number {}: {}", phoneNumber, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching users by phone number " + phoneNumber);
        }
    }

    @Override
    public List<UserDto> getUsersByEmail(String email) {
        try {
            return userDao.findUsersByEmail(email);
        } catch (Exception e) {
            log.error("An error occurred while fetching users by email {}: {}", email, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching users by email " + email);
        }
    }

    @Override
    public UserDto getApplicantById(Long userId) {
        try {
            UserDto applicant = userDao.findApplicantById(userId);
            if (applicant == null) {
                log.warn("Applicant with ID {} not found", userId);
                throw new CanNotFindUserException("Applicant with ID " + userId + " not found");
            }
            return applicant;
        } catch (Exception e) {
            log.error("An error occurred while fetching applicant with ID {}: {}", userId, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching applicant with ID " + userId);
        }
    }

    @Override
    public UserDto getEmployerById(Long userId) {
        try {
            UserDto employer = userDao.findEmployerById(userId);
            if (employer == null) {
                log.warn("Employer with ID {} not found", userId);
                throw new CanNotFindUserException("Employer with ID " + userId + " not found");
            }
            return employer;
        } catch (Exception e) {
            log.error("An error occurred while fetching employer with ID {}: {}", userId, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching employer with ID " + userId);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try {
            return userDao.existsByEmail(email);
        } catch (Exception e) {
            log.error("An error occurred while checking existence of email {}: {}", email, e.getMessage());
            throw new CanNotFindUserException("An error occurred while checking existence of email " + email);
        }
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        try {
            if (userDao.existsByEmail(userDto.getEmail())) {
                log.warn("User with email {} already exists", userDto.getEmail());
                throw new CanNotFindUserException("User with email " + userDto.getEmail() + " already exists");
            }

            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDto.setEnabled(true);

            return userDao.saveUser(userDto);
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
            UserDto existingUser = userDao.findUserById(updateUserProfileDto.getId());
            if (existingUser == null) {
                log.warn("User with ID {} not found", updateUserProfileDto.getId());
                throw new CanNotFindUserException("User with ID " + updateUserProfileDto.getId() + " not found");
            }

            existingUser.setName(updateUserProfileDto.getName());
            existingUser.setSurname(updateUserProfileDto.getSurname());
            existingUser.setAge(updateUserProfileDto.getAge());
            existingUser.setPhoneNumber(updateUserProfileDto.getPhoneNumber());

            return userDao.updateUser(existingUser);
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
            List<UserDto> users = userDao.findUsersByEmail(email);
            if (users.isEmpty()) {
                log.warn("User with email {} not found", email);
                throw new CanNotFindUserException("User with email " + email + " not found");
            }
            return users.get(0);
        } catch (Exception e) {
            log.error("An error occurred while fetching user by email {}: {}", email, e.getMessage());
            throw new CanNotFindUserException("An error occurred while fetching user by email " + email);
        }
    }
}

