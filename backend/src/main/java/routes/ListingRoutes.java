package routes;

import application.ListingLogic;
import application.Repositories;
import express.Express;
import entityDO.Listing;
import entityDO.User;
import dtos.AddListingDTO;
import service.ListingService;

import static java.lang.Integer.parseInt;

public class ListingRoutes {

    private ListingLogic listingLogic;
    private ListingService ls;
    private Express app;

    public ListingRoutes(Express app, Repositories repositories) {

        listingLogic= new ListingLogic(repositories);
        ls= new ListingService();
        this.app = app;

        getAllListings();

        app.post("/api/addListing", (req, res) -> {
            User currentUser = req.session("current-user");

            Listing createdListing = listingLogic.createNewListing(
                    ls.convertAddListingToListing(
                            req.body(AddListingDTO.class),
                            currentUser
                    )
            );

            req.session("current-Listing", createdListing);

            res.json(createdListing.getId());
        });



    }

    private void getAllListings(){
        //TODO CHECK IF NEEDED TO USE DTO
        app.get("/api/getAllListings", (req, res) -> {
            res.json(listingLogic.getAllListings());
        });
    }

    private void getAllListingsInSummaryFromUser(){
            app.get("/rest/:userID/listings", (req, res) -> {
            int userID = parseInt(req.params("userID"));
            res.json(listingLogic.getAllListingsInSummaryFromUser(userID));
        });
    }

}
