package routes.booking;

import application.booking.BookingLogic;
import express.Express;
import models.Booking;
import repositories.BookingRepository;

public class BookingRoutes {

    BookingRepository bookingRepository;
    BookingLogic bookingLogic;

    public BookingRoutes(Express app, BookingRepository bookingRepository) {
        createBookingRoute(app);
        this.bookingRepository = bookingRepository;
        this.bookingLogic = new BookingLogic(bookingRepository);
    }

    private void createBookingRoute(Express app){
        app.post("/api/createBooking", ((req, res) -> {
            Booking newBooking = req.body(Booking.class);
            newBooking = bookingLogic.createAndGetConfirmationBooking(newBooking);
            System.out.println(newBooking);
        }));
    }
}
