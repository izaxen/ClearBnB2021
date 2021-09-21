package routes.listing;

import application.listing.ListingLogic;
import express.Express;
import models.Listing;
import repositories.ListingRepository;

public class ListingRoutes {

    private ListingLogic listingLogic;

    public ListingRoutes(Express app, ListingRepository listingRepository) {

        listingLogic= new ListingLogic(listingRepository);

        app.post("/api/addListing", (req, res) -> {   //Create listing
            System.out.println("yes1");
            Listing listing = listingLogic.createNewListing(req.body(Listing.class));
            System.out.println("yes");
            res.json(listing);
        });

    }
}
