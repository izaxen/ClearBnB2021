package repositories;

import jakarta.persistence.EntityManager;
import entityDO.Amenities;

public class AmenitiesRepository {

    private EntityManager entityManager;

    public AmenitiesRepository(EntityManager entityManager){
        this.entityManager = entityManager;
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

}
