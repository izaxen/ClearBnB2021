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

    public List<User> findByFullName(String firstName, String surName){
        EntityManager em = emf.createEntityManager();
        List<User> findByFullName = em.createQuery("SELECT u FROM User u WHERE u.firstName = :firstName AND u.surName = :surName", User.class)
                .setParameter("firstName", firstName)
                .setParameter("surName", surName)
                .getResultList();
        em.close();
        return findByFullName;
    }

    public List<User> findByFullNameQuery(String firstName, String surName){
        EntityManager em = emf.createEntityManager();
        List<User> findByFullNameQuery = em.createNamedQuery("User.findByName", User.class)
                .setParameter("firstName", firstName)
                .setParameter("surName", surName)
                .getResultList();
        em.close();
        return  findByFullNameQuery;
    }

    public List<User> findAllUsers(){
        EntityManager em = emf.createEntityManager();
        List<User> findAllUsers = em.createNamedQuery("User.findAllUsers").getResultList();
        em.close();
        return findAllUsers;
    }

    public Optional<User> addUser(User user){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
            return Optional.of(user);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return user != null ? Optional.of(user):Optional.empty();
    }


    public List<User> findAll(){
        EntityManager em = emf.createEntityManager();
        List<User> findAll = em.createQuery("from User").getResultList();
        em.close();
        return findAll;
    }

    public Optional<User> findByName(String name){
        EntityManager em = emf.createEntityManager();
        User user = em.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return user != null ? Optional.of(user) : Optional.empty();
        }

    public Optional<User> findByNameNamedQuery(String name){
        EntityManager em = emf.createEntityManager();
        User user = em.createNamedQuery("User.findByName", User.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return user !=null ? Optional.of(user) : Optional.empty();
    }

    public Optional<User> findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        User user = null;
        try{
        user = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();}

        catch (NoResultException e){
        //Do nothing Dennis
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
            em.clear();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        em.close();
        return user;
    }

    public void updateUserFirstName(String firstName, Integer id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE User u SET u.firstName = :firstName WHERE u.ID = :ID")
            .setParameter("firstName", firstName)
                .setParameter("ID", id)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
//    public User updateUserFirstName(String firstName, String lastName){
//        return entityManager.createNamedQuery("User.updateUser2", User.class)
//                .setParameter("firstName", firstName)
//                .setParameter("lastName", lastName)
//                .getSingleResult();
//    }
    public void updateName(String name, User user){
        

    }

}
