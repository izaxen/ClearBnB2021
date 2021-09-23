package routes;

import application.booking.BookingLogic;
import express.Express;
import models.Booking;
import repositories.BookingRepository;

public class BookingRoutes {

    BookingRepository bookingRepository;
    BookingLogic bookingLogic;
    Express app;

    public BookingRoutes(Express app, BookingRepository bookingRepository) {
        this.app = app;
        this.bookingRepository = bookingRepository;
        this.bookingLogic = new BookingLogic(bookingRepository);

        createBookingRoute();
    }

    private void createBookingRoute(){
        app.post("/api/createBooking", ((req, res) -> {
            Booking newBooking = bookingLogic.createAndGetConfirmationBooking(req.body(Booking.class));
            System.out.println(newBooking);
        }));
    }
}
