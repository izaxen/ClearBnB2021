package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import entityDO.User;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Optional;

public class UserRepository {
    private EntityManagerFactory emf;

    public UserRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Optional<User> findById(int id){
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public User findUserById(int id){
        EntityManager em = emf.createEntityManager();
        try{
            User user = em.find(User.class, id);
            em.close();
            return user;
        }catch (PersistenceException e){
            System.out.println("PersistenceException IN findByUser-----------:\n" + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("Exception IN findByUser-----------:\n" + e.getMessage());
            e.printStackTrace();
        }

        em.close();
        return null;

    }

    public Optional<User> findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        User user = null;
        try{
        user = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();}

        catch (NoResultException e){
        }
        em.close();
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public User save(User user){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        em.close();
        return user;
    }

    public User update(User user){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        em.close();
        return user;
    }
}
