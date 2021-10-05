package mapper;

import dtos.AddListingDTO;
import dtos.FilteredListingDTO;
import dtos.UpdateListingDTO;
import entityDO.Listing;
import entityDO.User;

public class ListingMapper {

    public Listing convertAddListingToListing(AddListingDTO dto, User owner) {
        return new Listing(dto.getPrice(), dto.getDescription(), dto.getAvailableStartDate(), dto.getAvailableEndDate(), owner);
    }

    public Listing convertUpdateListingToListing(UpdateListingDTO dto, User owner) {
        return new Listing(dto.getId(), dto.getPrice(), dto.getDescription(), dto.getAvailableStartDate(), dto.getAvailableEndDate(), owner);
    }

    public FilteredListingDTO convertListingToFilteredDTO(Listing listing) {
        return new FilteredListingDTO(listing.getId(), listing.getDescription(), (int) Math.ceil(listing.getPrice() * 1.15), listing.getAddress().getCity(), listing.getAddress().getAddressListing());
    }
}
