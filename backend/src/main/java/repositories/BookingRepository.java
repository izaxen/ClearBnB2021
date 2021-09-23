package repositories;

import jakarta.persistence.EntityManager;
import entityDO.Booking;

import java.util.List;
import java.util.Optional;

public class BookingRepository {
    private EntityManager entityManager;

    public BookingRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<Booking> findById(Integer id){
        Booking booking = entityManager.find(Booking.class, id);
        return booking != null ? Optional.of(booking) : Optional.empty();
    }

    public List<Booking> findAllBookings(){
        return entityManager.createQuery("from Booking").getResultList();
    }

    public Optional<Booking> addBooking(Booking booking){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(booking);
            entityManager.getTransaction().commit();
            return Optional.of(booking);
        }
        catch (Exception ex){
           ex.printStackTrace();
        }
        return Optional.empty();
    }
}
