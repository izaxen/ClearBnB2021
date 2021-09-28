package routes;

import application.BookingLogic;
import application.Repositories;
import dtos.AddBookingDTO;
import entityDO.User;
import express.Express;
import mapper.BookingService;

import static java.lang.Integer.parseInt;

public class BookingRoutes {

    Repositories repositories;
    BookingLogic bookingLogic;
    Express app;

    public BookingRoutes(Express app, Repositories repositories) {
        this.app = app;
        this.repositories = repositories;
        this.bookingLogic = new BookingLogic(repositories);

        routeCreateBooking();
        routeCreateBookingREST();
    }

    private void routeCreateBooking(){

        // TODO NOT WORKING CODE
        /*app.post("/api/createBooking1/:listingID", ((req, res) -> {

            User currentUser = req.session("current-user");
            if(currentUser == null){
                return;
            }

            int listingID = parseInt(req.params("listingID"));

            Booking createdBooking = bookingLogic.createNewBooking1(
                    currentUser,bookingService.convertBookingDTOIntoBooking(req.body(AddBookingDTO.class),
                            repositories.getListingRepository().findById(listingID).get());

        }));*/
    }

    private void routeCreateBookingREST(){
        app.get("/rest/createBooking/:listingID/:startDate/:endDate", ((req, res) -> {

            User currentUser = req.session("current-user");
            if(currentUser == null){
                res.json("Cannot create booking, not logged in.");
                return;
            }

            int listingID = parseInt(req.params("listingID"));
//            System.out.println(listingID);
//            System.out.println(req.params());
            String startDate = req.params("startDate");
            String endDate = req.params("endDate");

            AddBookingDTO dto = new AddBookingDTO(startDate, endDate);
            res.json(bookingLogic.createNewBooking(currentUser, dto, listingID));

        }));
    }
}
