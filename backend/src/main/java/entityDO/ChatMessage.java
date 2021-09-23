package entityDO;

import jakarta.persistence.*;

@Entity
@Table(name="chat_msg")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="chat_msg_id")
    private Integer ID;

    @ManyToOne
    @JoinColumn(name="current_chat_id")
    private CurrentChat currentChat;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column (name="message")
    private String message;

    public ChatMessage() {
    }

    public ChatMessage(CurrentChat currentChat, User user, String message) {
        this.currentChat = currentChat;
        this.user = user;
        this.message = message;
    }
}
