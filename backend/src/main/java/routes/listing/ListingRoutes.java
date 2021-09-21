package routes.listing;

import application.listing.ListingLogic;
import express.Express;
import models.Listing;
import repositories.ListingRepository;

import java.util.List;

public class ListingRoutes {

    private ListingLogic listingLogic;

    public ListingRoutes(Express app, ListingRepository listingRepository) {

        listingLogic= new ListingLogic(listingRepository);

        app.post("/api/addListing", (req, res) -> {   //Create listing
            Listing listing = listingLogic.createNewListing(req.body(Listing.class));
            res.json(listing);
        });

        app.get("/api/getListings", (req,res) ->{
            List<Listing> listings = listingLogic.getAllListings();
            res.json(listings);
        });

    }
}
