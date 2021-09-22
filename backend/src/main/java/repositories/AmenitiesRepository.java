package repositories;

import jakarta.persistence.EntityManager;
import models.Amenities;

import java.util.Optional;

public class AmenitiesRepository {

    private EntityManager entityManager;

    public AmenitiesRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<Amenities> addAmenities(Amenities amenities){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(amenities);
            entityManager.getTransaction().commit();
            return Optional.of(amenities);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

}
