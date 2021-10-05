package routes;

import application.BookingLogic;
import application.Repositories;
import dtos.AddBookingDTO;
import entityDO.User;
import express.Express;

import static java.lang.Integer.parseInt;

public class BookingRoutes {

    Repositories repositories;
    BookingLogic bookingLogic;
    Express app;

    public BookingRoutes(Express app, Repositories repositories) {
        this.app = app;
        this.repositories = repositories;
        this.bookingLogic = new BookingLogic(repositories);

        createBookingRoute();
    }


    private void createBookingRoute(){
        app.post("/api/createBooking", ((req, res) -> {

            User currentUser = req.session("current-user");
            if(currentUser == null){
                res.json("Cannot create booking, not logged in.");
                return;
            }

            res.json(bookingLogic.createNewBooking(currentUser, req.body(AddBookingDTO.class)));

        }));
    }
}
