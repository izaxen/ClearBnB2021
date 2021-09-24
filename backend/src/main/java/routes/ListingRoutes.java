package routes;

import application.ListingLogic;
import application.Repositories;
import dtos.UpdateListingDTO;
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

    public ListingRoutes(Express app, ListingRepository listingRepository) {

        listingLogic= new ListingLogic(listingRepository);
        ls= new ListingService();
        repo = new Repositories();

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

            System.out.println(repo.getListingRevisionRepository().findRevIDByID(updatedListing.getId()).toString());
            req.session("current-rev-list-id", repo.getListingRevisionRepository().findRevIDByID(updatedListing.getId()));

            res.json(updatedListing.getId());
        });

    }
}
