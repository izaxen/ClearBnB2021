package models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="current_chat")
public class CurrentChat {

    @Id
    @Column(name="current_chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="closed")
    private Boolean isClosed;

    @OneToMany(mappedBy = "currentChat")
    private List<ChatMessage> chatMessages = new ArrayList<>();

    public Boolean getClosed() {
        return isClosed;
    }

    public List getChatMessages() {
        return chatMessages;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }
}
