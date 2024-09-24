package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.model.customModel.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @GetMapping
    public String chat(Model model, Authentication authentication) {
        String email = authentication.getName();
        model.addAttribute("email", email);
        return "chat/chat";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, Authentication authentication) {
        chatMessage.setSender(authentication.getName());
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor, Authentication authentication) {
        String email = authentication.getName();
        headerAccessor.getSessionAttributes().put("username", email);
        chatMessage.setSender(email);
        return chatMessage;
    }
}
