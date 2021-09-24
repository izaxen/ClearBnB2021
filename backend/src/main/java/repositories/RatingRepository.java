package repositories;

import entityDO.User;
import jakarta.persistence.EntityManager;
import entityDO.Rating;

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

    public List<Rating> getRatingOfUser(User user){
        return entityManager.createQuery("SELECT r FROM Rating r WHERE r.recipient = :user", Rating.class)
                .setParameter("user", user)
                .getResultList();
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
