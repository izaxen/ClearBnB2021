package application.listing;

import models.Listing;
import repositories.ListingRepository;

import java.util.List;

public class ListingLogic {

    ListingRepository listingRepository;

    public ListingLogic(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public ListingLogic() {
    }

    public Listing createNewListing(Listing listing) {
        return listingRepository.addListing(listing);
    }

    public List<Listing> getAllListings(){
        return listingRepository.findAllListings();
    }
}
