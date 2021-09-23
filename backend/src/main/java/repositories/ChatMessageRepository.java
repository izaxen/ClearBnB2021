package repositories;

import jakarta.persistence.EntityManager;
import entityDO.ChatMessage;


import java.util.Optional;

public class ChatMessageRepository {
    private EntityManager entityManager;

    public ChatMessageRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<ChatMessage> addChatMessage(ChatMessage chatMessage){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(chatMessage);
            entityManager.getTransaction().commit();
            return Optional.of(chatMessage);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
