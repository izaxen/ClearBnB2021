package repositories;

import jakarta.persistence.EntityManager;
import entityDO.ChatMessage;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;


import java.util.List;
import java.util.Optional;

public class ChatMessageRepository {
    private EntityManagerFactory emf;


    public ChatMessageRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Optional<ChatMessage> addChatMessage(ChatMessage chatMessage){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(chatMessage);
            em.getTransaction().commit();
            em.close();
            return Optional.of(chatMessage);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return Optional.empty();
    }

    public List<ChatMessage> findMessagesBySenderID(int senderID, int receiverID){
        EntityManager em= emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        List<ChatMessage> list =  session.createQuery("Select m FROM ChatMessage m WHERE " +
                "(m.sender.id = :senderID " +
                        "AND m.receiver.id = :receiverID) " +
                        "OR (m.sender.id = :receiverID " +
                        "AND m.receiver.id = :senderID) " +
                        "ORDER BY m.dateCreated", ChatMessage.class)
                .setParameter("senderID", senderID)
                .setParameter("receiverID", receiverID)
                .getResultList();
        em.close();
        return list;
    }
}
