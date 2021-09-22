package application.listing;

import models.Listing;
import repositories.ListingRepository;

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
}
