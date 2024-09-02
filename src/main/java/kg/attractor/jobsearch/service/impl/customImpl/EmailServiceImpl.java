package kg.attractor.jobsearch.service.impl.customImpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@Slf4j
@RequiredArgsConstructor

public class EmailServiceImpl {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String EMAIL_FROM;

    public void sendEmail(String toEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(EMAIL_FROM, "JobSearch Support");
        helper.setTo(toEmail);

        String subject = "Ссылка для сброса пароля";
        String content = "<p>Здравствуйте, </p>\n" +
                "<p>Вы запросили ссылку для сброса пароля.</p>\n" +
                "<p>Нажмите на ссылку ниже, чтобы изменить свой пароль:</p>\n" +
                "<p> <a href=\"" + link + "\">Изменить пароль</a></p>\n" +
                "<br>\n" +
                "<p>Проигнорируйте это письмо, если вы помните пароль или не отправили запрос.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}
