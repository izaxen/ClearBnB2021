package repositories;

import jakarta.persistence.EntityManager;
import entityDO.Listing;

import java.util.List;
import java.util.Optional;

public class ListingRepository {
    private EntityManager entityManager;

    public ListingRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<Listing> findById (Integer id){
        Listing listing = entityManager.find(Listing.class, id);
        return listing != null ? Optional.of(listing) : Optional.empty();
    }

    public List<Listing> findAllListings(){
        return entityManager.createQuery("from Listing").getResultList();
    }

    public Listing addListing(Listing listing){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(listing);
            entityManager.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return listing;
    }

    public Listing updateListing(Listing listing){

        try{
            entityManager.getTransaction().begin();
            entityManager.merge(listing);
            entityManager.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return listing;
    }




}
