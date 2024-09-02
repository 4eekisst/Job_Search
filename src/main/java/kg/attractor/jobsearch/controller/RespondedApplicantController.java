package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.RespondedApplicantDto;
import kg.attractor.jobsearch.model.Message;
import kg.attractor.jobsearch.model.RespondedApplicant;
import kg.attractor.jobsearch.service.MessageService;
import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("")

public class RespondedApplicantController {
    private final RespondedApplicantService respondedApplicantService;
    private final MessageService messageService;

    @PostMapping("/respond")
    public String respond(@RequestParam Long resumeId, @RequestParam String message,
                                   @RequestParam Long vacancyId, RedirectAttributes redirectAttributes) {
        try {
            RespondedApplicantDto respondedApplicantDto = RespondedApplicantDto.builder()
                    .resumeId(Math.toIntExact(resumeId))
                    .vacancyId(Math.toIntExact(vacancyId))
                    .confirmation(true)
                    .build();

            respondedApplicantService.respondToVacancy(respondedApplicantDto);

            RespondedApplicant respondedApplicant = respondedApplicantService.findRespondedApplicant(respondedApplicantDto);

            Message msg = Message.builder()
                    .text(message)
                    .timeStamp(LocalDate.now())
                    .respondedApplicant(respondedApplicant)
                    .build();
            messageService.saveMessage(msg);

            redirectAttributes.addFlashAttribute("message", "Отклик успешно отправлен.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Не удалось отправить отклик.");
        }

        return "redirect:/vacancy/info/" + vacancyId;
    }
}
