package routes;

import application.ListingLogic;
import express.Express;
import entityDO.Listing;
import entityDO.User;
import dtos.AddListingDTO;
import repositories.ListingRepository;
import service.ListingService;

public class ListingRoutes {

    private ListingLogic listingLogic;
    private ListingService ls;

    public ListingRoutes(Express app, ListingRepository listingRepository) {

        listingLogic= new ListingLogic(listingRepository);
        ls= new ListingService();

        app.post("/api/addListing", (req, res) -> {
            User currentUser = req.session("current-user");

            Listing createdListing = listingLogic.createNewListing(
                    ls.convertAddListingToListing(
                            req.body(AddListingDTO.class),
                            currentUser
                    )
            );

            res.json(createdListing.getId());
            System.out.println(createdListing.getId());
        });

    }
}
