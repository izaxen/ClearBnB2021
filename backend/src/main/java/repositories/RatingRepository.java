package repositories;

import entityDO.Booking;
import entityDO.User;
import jakarta.persistence.EntityManager;
import entityDO.Rating;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RatingRepository {

    private EntityManagerFactory emf;

    public RatingRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Optional<Rating> addRating(Rating rating){
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(rating);
            em.getTransaction().commit();
            em.close();
            return Optional.of(rating);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return Optional.empty();
    }

    public List<Rating> getRatingsLinkedToBooking(Booking booking){
        EntityManager em = emf.createEntityManager();

        List<Rating> getRatingsToFill;
        try {
            getRatingsToFill = em.createQuery("SELECT r FROM Rating r WHERE r.booking = :booking", Rating.class)
                    .setParameter("booking", booking)
                    .getResultList();
            em.close();
            return getRatingsToFill;
        }catch (PersistenceException e){
            e.printStackTrace();
        }
        em.close();
        return null;
    }

    public List<Rating> getRatingOfUser(User user){
        EntityManager em = emf.createEntityManager();

        List<Rating> ratingList;
        try {
            ratingList = em.createQuery("SELECT r FROM Rating r WHERE r.recipient = :user", Rating.class)
                    .setParameter("user", user)
                    .getResultList();
            em.close();
            return ratingList;
        }catch (PersistenceException e){
            e.printStackTrace();
        }
        em.close();
        return null;
    }

    public double calcAvgRatingOfUser(User user){
        EntityManager em = emf.createEntityManager();
        double avg = 0;
        try{
            double average = (double) em.createQuery("SELECT avg(r.rating) FROM Rating r WHERE r.recipient = :user")
                    .setParameter("user", user)
                    .getSingleResult();

            if(average > 0){
                avg = average;
            }

        }catch (Exception ignored){
        }
        em.close();
        return avg;

    }

    public boolean deleteRating(int id){
        EntityManager em = emf.createEntityManager();
        boolean didDelete = false;
        try{
            Rating rating = em.find(Rating.class, id);
            em.getTransaction().begin();
            em.remove(rating);
            em.getTransaction().commit();
            didDelete = true;
        }catch (Exception e){
            System.out.println("Error in deleteRating Repository");
        }

        em.close();
        return didDelete;
    }

}
