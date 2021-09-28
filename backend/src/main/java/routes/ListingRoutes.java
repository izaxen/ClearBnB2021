package routes;

import application.ListingLogic;
import application.Repositories;
import dtos.ListingFilterDTO;
import dtos.UpdateListingDTO;
import entityDO.ListingRevision;
import com.mysql.cj.Session;
import dtos.FilteredListingDTO;
import express.Express;
import entityDO.Listing;
import entityDO.User;
import dtos.AddListingDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import oracle.jdbc.OracleConnection;
import org.hibernate.annotations.Filter;
import repositories.ListingRepository;
import repositories.ListingRevisionRepository;
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
            Listing createdListing = listingLogic.createNewListing(
                            req.body(AddListingDTO.class),
                            currentUser
                    );
            req.session("current-Listing", createdListing);
            res.json(createdListing.getId());
        });


        app.get("/api/getAllListings", (req, res) -> {
            res.json(listingLogic.getAllListingsDTO());
        });

        app.post("/api/updateListing", (req, res) -> {
            User currentUser = req.session("current-user");
            Listing updatedListing = listingLogic.updateListing(
                    ls.convertupdateListingToListing(
                            req.body(UpdateListingDTO.class),
                            currentUser
                    )
            );
            req.session("current-Listing", updatedListing);
            res.json(updatedListing.getId());
        });


        app.post("/api/getFilteredListing", (req, res) ->{
            res.json(listingLogic.getFilteredListings(
                    (req.body(ListingFilterDTO.class))));
        });

    }
    private void getAllListingsInSummaryFromUser() {
        app.get("/rest/:userID/listings", (req, res) -> {
            int userID = parseInt(req.params("userID"));
            res.json(listingLogic.getAllListingsInSummaryFromUser(userID));
        });
    }
}
