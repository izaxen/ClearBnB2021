package repositories;

import jakarta.persistence.EntityManager;
import entityDO.CurrentChat;
import jakarta.persistence.EntityManagerFactory;

import java.util.Optional;

public class CurrentChatRepository {

    private EntityManagerFactory emf;

    public CurrentChatRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Optional<CurrentChat> findById(Integer id){
        EntityManager em = emf.createEntityManager();
        CurrentChat currentChat = em.find(CurrentChat.class, id);
        em.close();
        return currentChat != null ? Optional.of(currentChat) : Optional.empty();
    }

    public Optional<CurrentChat> addCurrentChat(CurrentChat currentChat){
        EntityManager em= emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(currentChat);
            em.getTransaction().commit();
            em.close();
            return Optional.of(currentChat);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return Optional.empty();
    }

    public Optional<CurrentChat> updateCurrentChat(CurrentChat currentChat){
        EntityManager em= emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.merge(currentChat);
            em.getTransaction().commit();
            em.close();
            return Optional.of(currentChat);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return Optional.empty();
    }

}
