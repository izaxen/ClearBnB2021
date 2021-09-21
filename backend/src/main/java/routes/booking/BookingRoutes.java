package routes.booking;

import express.Express;
import repositories.BookingRepository;

public class BookingRoutes {

    BookingRepository bookingRepository;

    public BookingRoutes(Express app, BookingRepository bookingRepository) {
        createBookingRoute(app);
        this.bookingRepository = bookingRepository;
    }

    private void createBookingRoute(Express app){
        app.post("/api/createBooking", ((req, res) -> {
            System.out.println(req.body());
        }));
    }
}
