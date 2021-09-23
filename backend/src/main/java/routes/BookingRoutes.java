package routes;

import application.BookingLogic;
import application.Repositories;
import dtos.AddBookingDTO;
import entityDO.Booking;
import entityDO.Listing;
import entityDO.User;
import express.Express;
import service.BookingService;

import static java.lang.Integer.parseInt;

public class BookingRoutes {

    Repositories repositories;
    BookingLogic bookingLogic;
    Express app;
    BookingService bookingService = new BookingService();

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
                return;
            }

            int listingID = parseInt(req.params("listingID"));
            String startDate = req.params("startDate");
            String endDate = req.params("startDate");

            AddBookingDTO dto = new AddBookingDTO(startDate, endDate);
            bookingLogic.createNewBooking(currentUser, dto, listingID);

        }));
    }
}
