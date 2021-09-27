package repositories;

import entityDO.Listing;
import jakarta.persistence.EntityManager;
import entityDO.Amenities;

import java.util.Optional;

public class AmenitiesRepository {

    private EntityManager entityManager;

    public AmenitiesRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<Amenities> findById (Integer id){
        Amenities amenities = entityManager.find(Amenities.class, id);
        return amenities != null ? Optional.of(amenities) : Optional.empty();
    }


    public Amenities addAmenities(Amenities amenities){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(amenities);
            entityManager.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            entityManager.clear();
        }
        return amenities;
    }

    public Amenities updateAmenities(Amenities amenities){
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(amenities);
            entityManager.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return amenities;

    }



}
