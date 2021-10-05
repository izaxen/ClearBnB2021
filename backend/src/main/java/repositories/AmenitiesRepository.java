package repositories;
import entityDO.Listing;
import jakarta.persistence.EntityManager;
import entityDO.Amenities;
import jakarta.persistence.EntityManagerFactory;

import java.util.Optional;

public class AmenitiesRepository {

    private EntityManagerFactory emf;

    public AmenitiesRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Optional<Amenities> findById (Integer id){
        EntityManager em = emf.createEntityManager();
        Amenities amenities = em.find(Amenities.class, id);
        em.close();
        return amenities != null ? Optional.of(amenities) : Optional.empty();
    }

    public Amenities updateAmenities(Amenities amenities, Listing listing){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(amenities);
            em.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        em.close();
        return amenities;

    }



}
