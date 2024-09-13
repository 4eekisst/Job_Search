package kg.attractor.jobsearch.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.dto.customDto.UpdateUserProfileDto;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.ImageService;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@RequestMapping("")

public class ProfileController {
    private final UserService userService;
    private final ImageService imageService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size) {
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);

        model.addAttribute("user", user);

        if (user.getAuthorityId() == 1) {
            Page<ResumeDto> resumesPage = resumeService.getResumesByUserId(user.getId(), page, size);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            resumesPage.forEach(resume -> resume.setCreatedDateStr(resume.getCreatedDate().format(formatter)));
            model.addAttribute("resumesPage", resumesPage);
        } else if (user.getAuthorityId() == 2) {
            Page<VacancyDto> vacanciesPage = vacancyService.getVacanciesByUserId(user.getId(), page, size);
            model.addAttribute("vacanciesPage", vacanciesPage);
        }
        return "profile/profile";
    }

    @GetMapping("/edit")
    public String edit(Model model, Authentication authentication) {
        UserDto user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("authority_id", user.getAuthorityId());
        return "profile/edit";
    }

    @PostMapping("/edit")
    public String edit(Model model, Authentication authentication,
                       @ModelAttribute UpdateUserProfileDto updateUserProfileDto,
                       @RequestParam("file") MultipartFile file) {

        UserDto user = userService.getUserByEmail(authentication.getName());
        updateUserProfileDto.setId(user.getId());

        imageService.uploadImage(file, authentication);
        userService.updateUserProfile(updateUserProfileDto);

        model.addAttribute("accType", user.getAuthorityId());
        return "redirect:/profile";
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "password_recovery/forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest httpServletRequest, Model model) {
        try {
            userService.makeResetPasswordLnk(httpServletRequest);
            model.addAttribute("message", "Мы отправили ссылку для сброса пароля на вашу электронную почту. Пожалуйста, проверьте.");
        } catch (UsernameNotFoundException | UnsupportedEncodingException e) {
            model.addAttribute("error", e.getMessage());
        } catch (MessagingException e) {
            model.addAttribute("error", "Ошибка при отправке электронной почты");
        }
        return "password_recovery/forgot_password_form";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam String token, Model model){
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", "Неверный токен");
        }
        return "password_recovery/reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest httpServletRequest, Model model){
        String token = httpServletRequest.getParameter("token");
        String password = httpServletRequest.getParameter("password").trim();

        if (password.isEmpty()) {
            model.addAttribute("message", "Пароль не может быть пустым или содержать пробелы");
            return "password_recovery/reset_password_form";
        }

        try {
            User userModel = userService.getByResetPasswordToken(token);
            userService.updatePassword(userModel, password);
            model.addAttribute("message", "Вы успешно сменили пароль");
        } catch (UsernameNotFoundException e) {
            model.addAttribute("message", "Неверный токен");
        }
        return "password_recovery/message";
    }
}
