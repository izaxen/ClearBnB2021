package repositories;

import jakarta.persistence.EntityManager;
import models.CurrentChat;

import java.util.Optional;

public class CurrentChatRepository {

    private EntityManager entityManager;

    public CurrentChatRepository(EntityManager entityManager){
        this.entityManager = entityManager;
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
