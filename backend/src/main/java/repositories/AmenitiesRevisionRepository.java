package repositories;

import entityDO.AmenitiesRevision;
import entityDO.ListingRevision;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class AmenitiesRevisionRepository {
    private EntityManager entityManager;
    public AmenitiesRevisionRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<AmenitiesRevision> addAmenitiesRevision(AmenitiesRevision amenitiesRevision){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(amenitiesRevision);
            entityManager.getTransaction().commit();
            return Optional.of(amenitiesRevision);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
