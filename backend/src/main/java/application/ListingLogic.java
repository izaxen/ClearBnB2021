package application;

import dtos.AddListingDTO;
import entityDO.Listing;
import entityDO.User;
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
