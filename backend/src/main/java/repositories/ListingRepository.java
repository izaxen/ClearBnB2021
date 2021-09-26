package repositories;

import dtos.FilteredListingDTO;
import jakarta.persistence.EntityManager;
import entityDO.Listing;
import org.hibernate.Filter;
import org.hibernate.Session;

import java.sql.Timestamp;
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
        return entityManager.createQuery("SELECT l FROM Listing l", Listing.class).getResultList();
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

}
