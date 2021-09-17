package repositories;

import jakarta.persistence.EntityManager;
import models.User;

import java.util.List;
import java.util.Optional;

public class UserRepository {
    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<User> findById(Integer id){
        User user = entityManager.find(User.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public List<User> findByName(String firstName){
        return entityManager.createNamedQuery("SELECT u FROM User u WHERE u.firstName = :firstName ) ", User.class)
                .setParameter("firstName", firstName)
                .getResultList();
    }

    public List<User> findAllUsers(){
        return entityManager.createQuery("from User").getResultList();
    }

    public Optional<User> addUser(User user){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return Optional.of(user);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
