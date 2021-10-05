package repositories;

import entityDO.Listing;
import entityDO.User;
import jakarta.persistence.EntityManager;
import entityDO.Booking;
import jakarta.persistence.EntityManagerFactory;

import java.text.SimpleDateFormat;

import java.util.*;

public class BookingRepository {
    private EntityManagerFactory emf;


    public BookingRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Optional<Booking> findById(int id){
        EntityManager em= emf.createEntityManager();
        Booking booking = em.find(Booking.class, id);
        em.close();
        return booking != null ? Optional.of(booking) : Optional.empty();
    }

    public List<Booking> findAUsersOldBookings(User user){

        EntityManager em= emf.createEntityManager();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        List<Booking> bookings = new ArrayList<>();

        try {
            bookings = em.createQuery("SELECT b FROM Booking b WHERE b.endDate < :date AND " +
                    "b.user = :user OR (b.endDate < :date AND b.listing.user = :user)", Booking.class)
                    .setParameter("date", strDate)
                    .setParameter("user", user)
                    .getResultList();


        }catch (jakarta.persistence.PersistenceException e){
            e.printStackTrace();
        }

        em.close();

        return bookings;
    }


    public boolean checkIfListingIsAlreadyBooked(String startDate, String endDate, Listing listing){

        // so Marcus is searching in our DB for one booking's listing that is matching our new booking's listing
        // then new booking's start date is between old booking's start and end date
        // then new booking's end date is between old booking's start and end date

        EntityManager em= emf.createEntityManager();

        List bookings = em.createQuery("SELECT b FROM Booking b WHERE b.listing = :listing " +
                "AND (b.startDate between :sDate AND :eDate " +
                        "OR b.endDate between :sDate AND :eDate)", Booking.class)
                .setParameter("listing", listing)
                .setParameter("sDate", startDate)
                .setParameter("eDate", endDate)
                .getResultList();

        em.close();

        return bookings.size() > 0;
    }

    public boolean checkListingAvailableDates(String startDate, String endDate, Listing listing){
        EntityManager em= emf.createEntityManager();
        List bookings = em.createQuery("FROM Listing as l WHERE l.id = :listing " +
                "AND (:selectedStartDate IS NULL or l.availableStartDate <= :selectedStartDate) AND " +
                "(:selectedEndDate IS NULL or l.availableEndDate >= :selectedEndDate)", Listing.class)
                .setParameter("listing", listing.getId())
                .setParameter("selectedStartDate", startDate)
                .setParameter("selectedEndDate", endDate)
                .getResultList();

        em.close();
        return bookings.size() > 0;
    }

    public List<Booking> findAllBookings(){
        EntityManager em= emf.createEntityManager();
        em.close();
        return em.createQuery("FROM Booking").getResultList();
    }

    public Optional<Booking> addBooking(Booking booking){

        EntityManager em= emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(booking);
            em.getTransaction().commit();
            em.close();
            return Optional.of(booking);
        }
        catch (Exception ex){
           ex.printStackTrace();
        }
        em.close();
        return Optional.empty();
    }

    public Optional<Booking> updateBooking(Booking booking){

        EntityManager em= emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.merge(booking);
            em.getTransaction().commit();
            em.close();
            return Optional.of(booking);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return Optional.empty();
    }
}
