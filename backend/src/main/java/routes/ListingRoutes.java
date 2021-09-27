package routes;

import application.ListingLogic;
import application.Repositories;
import com.mysql.cj.Session;
import dtos.FilteredListingDTO;
import express.Express;
import entityDO.Listing;
import entityDO.User;
import dtos.AddListingDTO;
import filter.ListingFilter;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import oracle.jdbc.OracleConnection;
import org.hibernate.annotations.Filter;
import repositories.ListingRepository;
import service.ListingService;

import java.sql.Timestamp;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ListingRoutes {

    private ListingLogic listingLogic;
    private ListingService ls;
    private Express app;

    public ListingRoutes(Express app, Repositories repositories) {

        listingLogic= new ListingLogic(repositories);
        ls= new ListingService();
        this.app = app;

        getAllListingsInSummaryFromUser();

        app.post("/api/addListing", (req, res) -> {
            User currentUser = req.session("current-user");

            // req.body(AddListingDTO.class) is a fake object we got from frontpage

            // ls.convertAddListingToListing(req.body(AddListingDTO.class),currentUser)
            // turns our fake into the real object
            // currentUser is sent because we have USER in our entityDO for Listing

            // listingLogic.createNewListing saved that object to our DB

            Listing createdListing = listingLogic.createNewListing(
                    ls.convertAddListingToListing(
                            req.body(AddListingDTO.class),
                            currentUser
                    )
            );

//            req.body(Listing.class)
//            System compares what we got from frontend to Class Listing, if it has all attributes?

            req.session("current-Listing", createdListing);

            res.json(createdListing.getId());
        });

        //TODO CHECK IF NEEDED TO USE DTO
        app.get("/api/getAllListings", (req, res) -> {
           res.json(listingLogic.getAllListings());

        });

        app.get("/api/getAllListingsDTO", (req, res) -> {
            res.json(listingLogic.getAllListingsDTO());
        });

        app.post("/api/getFilteredListing", (req, res) ->{
           // Listing list = req.body(Listing.class);


            List<AddListingDTO> filteredListings = listingLogic.getFilteredListings(
            (req.body(ListingFilter.class)));

            
//            System.out.println("filteredListings: " + filteredListings);

            res.json(filteredListings);
        });
    }

    private void getAllListingsInSummaryFromUser(){
        app.get("/rest/:userID/listings", (req, res) -> {
            int userID = parseInt(req.params("userID"));
            res.json(listingLogic.getAllListingsInSummaryFromUser(userID));
        });
    }


}
