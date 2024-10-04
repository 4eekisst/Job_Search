package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("auth")

public class AuthController {
    private final UserService userService;

    @GetMapping("login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("isEmployer", false);
        return "auth/register";
    }

    @PostMapping("register")
    public String register(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEmployer", userDto.getAuthorityId() != null && userDto.getAuthorityId() == 2);
            model.addAttribute("bindingResult", bindingResult);
            return "auth/register";
        }

        if (userDto.getEnabled() == null) {
            userDto.setEnabled(true);
        }

        userService.registerUser(userDto);

        boolean isEmployer = userDto.getAuthorityId() != null && userDto.getAuthorityId() == 2;
        model.addAttribute("isEmployer", isEmployer);
        model.addAttribute("userDto", userDto);

        return "redirect:/auth/login";
    }
}

