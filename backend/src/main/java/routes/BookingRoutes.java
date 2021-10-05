package routes;

import application.BookingLogic;
import application.LogicHandler;
import application.Repositories;
import dtos.AddBookingDTO;
import entityDO.User;
import express.Express;

import static java.lang.Integer.parseInt;

public class BookingRoutes {

    LogicHandler logicHandler;
    Express app;

    public BookingRoutes(Express app, LogicHandler logicHandler) {
        this.app = app;
        this.logicHandler = logicHandler;

        createBookingRoute();
    }


    private void createBookingRoute(){
        app.post("/api/createBooking", ((req, res) -> {

            User currentUser = req.session("current-user");
            if(currentUser == null){
                res.json("Cannot create booking, not logged in.");
                return;
            }

            res.json(logicHandler.getBookingLogic().createNewBooking(currentUser, req.body(AddBookingDTO.class)));

        }));
    }
}
