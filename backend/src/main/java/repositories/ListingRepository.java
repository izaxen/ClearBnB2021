package repositories;

import entityDO.Rating;
import entityDO.User;
import jakarta.persistence.EntityManager;
import entityDO.Listing;
import jakarta.persistence.PersistenceException;

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

    public List<Listing> findAllListingsFromUser(User user){
        List<Listing> listingList;
        try {
            listingList = entityManager.createQuery("SELECT l FROM Listing l WHERE l.user = :user", Listing.class)
                    .setParameter("user", user)
                    .getResultList();
            return listingList;
        }catch (PersistenceException e){
            System.out.println("ERROR IN findAllListingsFromUser (repository) ----------------: \n" + e.getMessage());
            e.printStackTrace();
        }
        return null;
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
