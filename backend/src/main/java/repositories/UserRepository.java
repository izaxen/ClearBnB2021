package repositories;

import jakarta.persistence.EntityManager;
import models.User;

import java.util.List;
import java.util.Optional;

public class UserRepository {
    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager){
        this.entityManager =entityManager;
    }

    public Optional<User> findById(Integer id){
        User user = entityManager.find(User.class, id);
        return user != null ? Optional.of(user):Optional.empty();
    }

    public List<User> findAll(){
        return entityManager.createQuery("from User").getResultList();
    }

    public Boolean findByEmail(String email){
        try{
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        return true;

        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public Optional<User> findByNameNamedQuery(String name){
        User user = entityManager.createNamedQuery("User.findByName", User.class)
                .setParameter("name", name)
                .getSingleResult();
        return user !=null ? Optional.of(user) : Optional.empty();
    }

    public User save(User user){
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public void updateName(String name, User user){


    }

}
