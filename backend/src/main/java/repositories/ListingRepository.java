package repositories;

import dtos.ListingFilterDTO;
import entityDO.Booking;
import entityDO.User;
import jakarta.persistence.EntityManager;
import entityDO.Listing;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListingRepository {
    private EntityManagerFactory emf;

    public ListingRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Optional<Listing> findById (Integer id){
        EntityManager em = emf.createEntityManager();
        Listing listing = em.find(Listing.class, id);
        em.close();
        return listing != null ? Optional.of(listing) : Optional.empty();
    }

    public List<Listing> findAllListings(){
        EntityManager em = emf.createEntityManager();
        List<Listing> findAllListings = em.createQuery("FROM Listing l", Listing.class)
                .getResultList();
        em.close();
        return findAllListings;
    }

    public List<Listing> findAllListingsFromUser(int userID){
        EntityManager em = emf.createEntityManager();
        List<Listing> listingList = new ArrayList<>();

        try {
            listingList = em.createQuery("FROM Listing l WHERE l.user.id = :user", Listing.class)
                    .setParameter("user", userID)
                    .getResultList();
        }catch (PersistenceException e){
            System.out.println("ERROR IN findAllListingsFromUser (repository) ----------------: \n" + e.getMessage());
        }catch (java.lang.NullPointerException e){
            System.out.println("ERROR IN findAllListingsFromUser (repository) ----------------: \n" + e.getMessage());
        }catch (org.hibernate.AssertionFailure e){
            System.out.println("ERROR IN findAllListingsFromUser (repository) ----------------: \n" + e.getMessage());
        }

        em.close();
        return listingList;
    }

    public User findOwnerOfListingWithABooking(Booking booking){

        EntityManager em = emf.createEntityManager();

        Listing listing = em.createQuery("SELECT l FROM Listing l WHERE l = :listing", Listing.class)
                .setParameter("listing", booking.getListing())
                .getSingleResult();

        User user = listing.getUser();

        em.close();
        return user;
    }    
    
    public List<Listing> filterListing(ListingFilterDTO filter){
        EntityManager em = emf.createEntityManager();

        Session session = em.unwrap(Session.class);
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

        em.close();
        return matchedListing;
    }

    public Listing addListing(Listing listing){
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(listing);
            em.getTransaction().commit();

        }catch (Exception ex){
            ex.printStackTrace();
        }

        em.close();
        return listing;
    }

    public Listing updateListing(Listing listing){

        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(listing);
            em.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return listing;
    }



}
