package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.model.Message;
import kg.attractor.jobsearch.repository.MessageRepository;
import kg.attractor.jobsearch.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
