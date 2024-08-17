package kg.attractor.jobsearch.errors.handler;

import kg.attractor.jobsearch.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound(Model model){
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());

        return "error/error";
    }
}
