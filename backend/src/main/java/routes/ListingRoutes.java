package routes;

import application.ListingLogic;
import application.LogicHandler;
import application.Repositories;
import com.mongodb.client.MongoCollection;
import dtos.AddListingDTO;
import dtos.ListingFilterDTO;
import dtos.SingeListingDTO;
import dtos.UpdateListingDTO;
import entityDO.Listing;
import entityDO.User;
import express.Express;

import java.util.List;

import static java.lang.Integer.parseInt;

public class ListingRoutes {
    private final ListingLogic listingLogic;
    private final Express app;

    public ListingRoutes(Express app, MongoCollection collection, LogicHandler logicHandler) {

        this.listingLogic = logicHandler.getListingLogic();
        this.app = app;

        getAllListingsInSummaryFromUser();
        createNewListing();
        allListings(collection);
        updateListing();
        filteredListing();
        singleListing();
        singleListingoldVersion();
    }

    private void getAllListingsInSummaryFromUser() {
        app.get("/rest/:userID/listings", (req, res) -> {
            int userID = parseInt(req.params("userID"));
            res.json(listingLogic.getAllListingsInSummaryFromUser(userID));
        });
    }

    private void createNewListing() {
        app.post("/api/listing", (req, res) -> {
            User currentUser = req.session("current-user");
            Listing createdListing = listingLogic.createNewListing(
                    req.body(AddListingDTO.class),
                    currentUser
            );
            req.session("current-Listing", createdListing);
        });
    }

    private void allListings(MongoCollection collection) {
        app.get("/api/allListings", (req, res) -> {
            res.json(listingLogic.getAllListFromMongoDB(collection));
        });
    }

    private void updateListing() {
        app.put("/api/listing", (req, res) -> {
            User currentUser = req.session("current-user");
            Listing updatedListing = listingLogic.updateListing(
                    req.body(UpdateListingDTO.class),
                    currentUser);
            req.session("current-Listing", updatedListing);
            res.json(updatedListing.getId());
        });
    }

    private void filteredListing() {
        app.post("/api/filteredListings", (req, res) -> {
            res.json(listingLogic.getFilteredListings(
                    (req.body(ListingFilterDTO.class))));
        });
    }

    private void singleListing() {
        app.get("/rest/getSingleListing/:id", (req, res) -> {
            int id = parseInt(req.params("id"));
            SingeListingDTO dto = listingLogic.getSingleListing(id);
            res.json(dto);
        });
    }

    private void singleListingoldVersion() {
        app.get("/rest/getSingleListingVersion/:id", (req, res) -> {
            int id = parseInt(req.params("id"));
            List<SingeListingDTO> dto = listingLogic.getoldVersion(id);
            res.json(dto);
        });
    }
}
