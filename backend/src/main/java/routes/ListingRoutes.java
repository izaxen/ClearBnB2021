package routes;

import application.ListingLogic;
import application.Repositories;
import dtos.UpdateListingDTO;
import entityDO.ListingRevision;
import express.Express;
import entityDO.Listing;
import entityDO.User;
import dtos.AddListingDTO;
import repositories.ListingRepository;
import repositories.ListingRevisionRepository;
import service.ListingService;

public class ListingRoutes {

    private ListingLogic listingLogic;
    private ListingService ls;
    private Repositories repo;

    public ListingRoutes(Express app, Repositories repo) {
        this.repo = repo;
        listingLogic= new ListingLogic(repo);
        ls= new ListingService();

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

        app.get("/api/getAllListings", (req, res) -> {
           res.json(listingLogic.getAllListings());
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
    }
}
