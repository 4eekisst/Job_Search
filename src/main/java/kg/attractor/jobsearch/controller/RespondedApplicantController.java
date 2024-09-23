package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("")

public class RespondedApplicantController {
    private final RespondedApplicantService respondedApplicantService;

    @PostMapping("/respond")
    public String respond(@RequestParam Long resumeId, @RequestParam String message,
                          @RequestParam Long vacancyId, RedirectAttributes redirectAttributes) {
        try {
            respondedApplicantService.respondToVacancy(resumeId, message, vacancyId);
            redirectAttributes.addFlashAttribute("message", "Отклик успешно отправлен.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Не удалось отправить отклик.");
        }

        return "redirect:/vacancy/info/" + vacancyId;
    }
}
