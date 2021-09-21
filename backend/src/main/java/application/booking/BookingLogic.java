package application.booking;

import models.Booking;
import repositories.BookingRepository;

public class BookingLogic {

    BookingRepository bookingRepository;

    public BookingLogic(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createAndGetConfirmationBooking(Booking booking){
        Booking newBooking = bookingRepository.addBooking(booking).get();
        return newBooking;
    }


}
