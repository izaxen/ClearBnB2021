package routes;

import application.ListingLogic;
import express.Express;
import entityDO.Listing;
import entityDO.User;
import dtos.AddListingDTO;
import repositories.ListingRepository;

public class ListingRoutes {

    private ListingLogic listingLogic;

    public ListingRoutes(Express app, ListingRepository listingRepository) {

        listingLogic= new ListingLogic(listingRepository);

        app.post("/api/addListing", (req, res) -> {
            User currentUser = req.session("current-user");

            Listing createdListing = listingLogic.createNewListing(
                    listingLogic.convertAddListingToListing(
                            req.body(AddListingDTO.class),
                            currentUser
                    )
            );

            res.json(createdListing.getId());
        });

    }
}
