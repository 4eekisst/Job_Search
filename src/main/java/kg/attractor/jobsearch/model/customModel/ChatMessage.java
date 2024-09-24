package kg.attractor.jobsearch.model.customModel;

import kg.attractor.jobsearch.enums.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;
}
