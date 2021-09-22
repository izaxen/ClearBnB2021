package repositories;

import jakarta.persistence.EntityManager;
import models.Rating;

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

}
