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
    @JoinColumn(name="sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name="receiver")
    private User receiver;

    @Column (name="message")
    private String message;

    public ChatMessage() {
    }

    public ChatMessage(CurrentChat currentChat, User sender, User receiver, String message) {
        this.currentChat = currentChat;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }
}
