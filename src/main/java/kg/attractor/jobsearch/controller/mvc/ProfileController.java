package kg.attractor.jobsearch.controller.mvc;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.customDto.UpdateUserProfileDto;
import kg.attractor.jobsearch.service.ImageService;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
@RequestMapping("")


public class ProfileController {
    private final UserService userService;
    private final ImageService imageService;

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);

        model.addAttribute("user", user);

        return "profile/profile";
    }

    @GetMapping("/edit")
    public String edit(Model model, Authentication authentication) {
        UserDto user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("authority_id", user.getAuthority_id());
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

        model.addAttribute("accType", user.getAuthority_id());
        return "redirect:/profile";
    }
}
