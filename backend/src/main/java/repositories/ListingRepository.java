package repositories;

import dtos.ListingFilterDTO;
import entityDO.User;
import jakarta.persistence.EntityManager;
import entityDO.Listing;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
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


        return entityManager.createQuery("FROM Listing l", Listing.class)

                .getResultList();
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

        }
        return null;
    }

    public List<Listing> filterListing(ListingFilterDTO filter){
        Session session = entityManager.unwrap(Session.class);
        String ts1 = filter.getAvailableStartDate();
        String ts2 = filter.getAvailableEndDate();
        Boolean isBathTub = filter.getBathTub();
        Boolean isParkingLot = filter.getParkingLot();
        Boolean isStove = filter.getStove();
        Boolean isDoubleBed = filter.getDoubleBed();
        Boolean isBubblePool = filter.getBubblePool();
        Boolean isBicycle = filter.getBicycle();
        Boolean isSauna = filter.getSauna();
        int maxPrice = filter.getPrice();


        List<Listing> matchedListing = session.createQuery("FROM Listing as l WHERE " +
                        "(:selectedStartDate IS NULL or l.availableStartDate <= :selectedStartDate) AND " +
                        "(:selectedEndDate IS NULL or l.availableEndDate >= :selectedEndDate) AND " +
                        "(:isBathTub IS NULL or :isBathTub IS FALSE or l.amenities.isBathTub IS :isBathTub) AND " +
                        "(:isParkingLot IS NULL or :isParkingLot IS FALSE or  l.amenities.isParkingLot IS :isParkingLot) AND " +
                        "(:isStove IS NULL or :isStove IS FALSE or l.amenities.isStove IS :isStove) AND " +
                        "(:isDoubleBed IS NULL or :isDoubleBed IS FALSE or  l.amenities.isDoubleBed IS :isDoubleBed) AND " +
                        "(:isBubblePool IS NULL or :isBubblePool IS FALSE or  l.amenities.isBubblePool IS :isBubblePool) AND " +
                        "(:isBicycle IS NULL or :isBicycle IS FALSE or l.amenities.isBicycle IS :isBicycle) AND " +
                        "(:isSauna IS NULL or :isSauna IS FALSE or  l.amenities.isSauna IS :isSauna) AND " +
                        "(:maxPrice = 0 or l.price <= :maxPrice)", Listing.class)
                .setParameter("selectedStartDate", ts1)
                .setParameter("selectedEndDate", ts2)
                .setParameter("isBathTub", isBathTub)
                .setParameter("isParkingLot", isParkingLot)
                .setParameter("isStove", isStove)
                .setParameter("isDoubleBed", isDoubleBed)
                .setParameter("isBubblePool", isBubblePool)
                .setParameter("isBicycle", isBicycle)
                .setParameter("isSauna", isSauna)
                .setParameter("maxPrice", maxPrice)
                .list();

        return matchedListing;
    }

    public Listing addListing(Listing listing){

        try{
            entityManager.getTransaction().begin();
            entityManager.persist(listing);
            entityManager.getTransaction().commit();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        entityManager.clear();
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
        entityManager.clear();
        return listing;
    }




}
