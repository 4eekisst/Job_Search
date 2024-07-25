package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    @Override
    public UserDto getUserById(Long userId) {
        UserDto user = userDao.findUserById(userId);
        if (user == null) {
            log.warn("User with ID {} not found", userId);
        }
        return user;
    }

    @Override
    public List<UserDto> getUsersByName(String name) {
        return userDao.findUsersByName(name);
    }

    @Override
    public List<UserDto> getUsersByPhoneNumber(String phoneNumber) {
        return userDao.findUsersByPhoneNumber(phoneNumber);
    }

    @Override
    public List<UserDto> getUsersByEmail(String email) {
        return userDao.findUsersByEmail(email);
    }

    @Override
    public UserDto getApplicantById(Long userId) {
        UserDto applicant = userDao.findApplicantById(userId);
        if (applicant == null) {
            log.warn("Applicant with ID {} not found", userId);
        }
        return applicant;
    }

    @Override
    public UserDto getEmployerById(Long userId) {
        UserDto employer = userDao.findEmployerById(userId);
        if (employer == null) {
            log.warn("Employer with ID {} not found", userId);
        }
        return employer;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }
}
