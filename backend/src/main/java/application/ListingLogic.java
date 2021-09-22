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

    public Listing convertAddListingToListing(AddListingDTO dto, User owner) {
        return new Listing(dto.getPrice(), dto.getDescription(), dto.getAvailableStartDate(), dto.getAvailableEndDate(), owner);
    }

    public Listing createNewListing(Listing listing) {
        return listingRepository.addListing(listing);
    }
}
