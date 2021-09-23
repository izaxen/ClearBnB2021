package repositories;

import jakarta.persistence.EntityManager;
import entityDO.CurrentChat;

import java.util.Optional;

public class CurrentChatRepository {

    private EntityManager entityManager;

    public CurrentChatRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<CurrentChat> findById(Integer id){
        CurrentChat currentChat = entityManager.find(CurrentChat.class, id);
        return currentChat != null ? Optional.of(currentChat) : Optional.empty();
    }

    public Optional<CurrentChat> addCurrentChat(CurrentChat currentChat){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(currentChat);
            entityManager.getTransaction().commit();
            return Optional.of(currentChat);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

}
