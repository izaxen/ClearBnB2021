package repositories;

import entityDO.AmenitiesRevision;
import entityDO.ListingRevision;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Optional;

public class AmenitiesRevisionRepository {
    private EntityManagerFactory emf;


    public AmenitiesRevisionRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Optional<AmenitiesRevision> addAmenitiesRevision(AmenitiesRevision amenitiesRevision){
        EntityManager em= emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(amenitiesRevision);
            em.getTransaction().commit();
            em.close();
            return Optional.of(amenitiesRevision);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return Optional.empty();
    }
}
