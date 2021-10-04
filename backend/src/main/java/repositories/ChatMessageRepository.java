package repositories;

import jakarta.persistence.EntityManager;
import entityDO.ChatMessage;
import org.hibernate.Session;


import java.util.List;
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

    public List<ChatMessage> findMessagesBySenderID(int senderID, int receiverID){
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("Select m FROM ChatMessage m WHERE " +
                "(m.sender.id = :senderID " +
                        "AND m.receiver.id = :receiverID) " +
                        "OR (m.sender.id = :receiverID " +
                        "AND m.receiver.id = :senderID) " +
                        "ORDER BY m.dateCreated", ChatMessage.class)
                .setParameter("senderID", senderID)
                .setParameter("receiverID", receiverID)
                .getResultList();
    }
}
