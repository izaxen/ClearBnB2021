package repositories;

import jakarta.persistence.EntityManager;
import entityDO.Listing;
import org.hibernate.Filter;
import org.hibernate.Session;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
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
        return entityManager.createNamedQuery("Listing.findAllListings").getResultList();
    }


    public Listing addListing(Listing listing){
        Session session = entityManager.unwrap(Session.class);
        Filter availableDateFilter = session.enableFilter("availableDateFilter");

        Timestamp ts1 = new Timestamp(new Date().getTime());
        Timestamp ts2 = new Timestamp(new Date().getTime());

        availableDateFilter.setParameter("availableStartDate", ts1);
        availableDateFilter.setParameter("availableEndDate", ts2);

        List<Listing> matchedListing = this.findAllListings();
        System.out.println(matchedListing);

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
