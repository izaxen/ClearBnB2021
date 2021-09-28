package repositories;

import entityDO.Booking;
import entityDO.User;
import jakarta.persistence.EntityManager;
import entityDO.Rating;
import jakarta.persistence.PersistenceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RatingRepository {

    private EntityManager entityManager;

    public RatingRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<Rating> addRating(Rating rating){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(rating);
            entityManager.getTransaction().commit();
            return Optional.of(rating);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Rating> getRatingsLinkedToBooking(Booking booking){
        List<Rating> getRatingsToFill;

        try {
            getRatingsToFill = entityManager.createQuery("SELECT r FROM Rating r WHERE r.booking = :booking", Rating.class)
                    .setParameter("booking", booking)
                    .getResultList();
            return getRatingsToFill;
        }catch (PersistenceException e){
            System.out.println("ERROR IN getRatingsToFill (repository) ----------------: \n" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public List<Rating> getRatingOfUser(User user){

        List<Rating> ratingList;
        try {
            ratingList = entityManager.createQuery("SELECT r FROM Rating r WHERE r.recipient = :user", Rating.class)
                    .setParameter("user", user)
                    .getResultList();
            return ratingList;
        }catch (PersistenceException e){
            System.out.println("ERROR IN getRatingOfUser (repository) ----------------: \n" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public double calcAvgRatingOfUser(User user){
        try{
            return (double) entityManager.createQuery("SELECT avg(r.rating) FROM Rating r WHERE r.recipient = :user")
                    .setParameter("user", user)
                    .getSingleResult();
        }catch (Exception e){
            return 0;
        }

    }

}
