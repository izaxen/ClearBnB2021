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

    public List<User> findByFullName(String firstName, String lastName){
        return entityManager.createQuery("SELECT u FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName", User.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    public List<User> findByFullNameQuery(String firstName, String lastName){
        return entityManager.createNamedQuery("User.findByName", User.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
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

    public void updateUserFirstName(String firstName, Integer id){
        entityManager.getTransaction().begin();
        entityManager.createQuery("UPDATE User u SET u.firstName = :firstName WHERE u.ID = :ID")
            .setParameter("firstName", firstName)
                .setParameter("ID", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

//    public User updateUserFirstName(String firstName, String lastName){
//        return entityManager.createNamedQuery("User.updateUser2", User.class)
//                .setParameter("firstName", firstName)
//                .setParameter("lastName", lastName)
//                .getSingleResult();
//    }
}
