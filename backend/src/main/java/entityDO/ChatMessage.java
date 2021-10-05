package entityDO;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Column(name="date_created")
    private String dateCreated;

    public ChatMessage() {
    }

    public ChatMessage(CurrentChat currentChat, User sender, User receiver, String message) {
        this.currentChat = currentChat;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateCreated = date.format(myFormatObj);
    }

    public Integer getID() {
        return ID;
    }

    public CurrentChat getCurrentChat() {
        return currentChat;
    }

    public void setCurrentChat(CurrentChat currentChat) {
        this.currentChat = currentChat;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
