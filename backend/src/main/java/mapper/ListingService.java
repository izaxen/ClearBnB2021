package mapper;

import dtos.AddListingDTO;
import dtos.UpdateListingDTO;
import dtos.FilteredListingDTO;
import entityDO.Listing;
import entityDO.User;

public class ListingService {

    // this converts DTO into a fake
    public Listing convertAddListingToListing(AddListingDTO dto, User owner) {
        return new Listing(dto.getPrice(), dto.getDescription(), dto.getAvailableStartDate(), dto.getAvailableEndDate(), owner);
    }

    // this converts DTO into a fake
    public Listing convertupdateListingToListing(UpdateListingDTO dto, User owner) {
        return new Listing(dto.getId(), dto.getPrice(), dto.getDescription(), dto.getAvailableStartDate(), dto.getAvailableEndDate(), owner);
    }


    public Listing convertToFilteredListingsDTO(FilteredListingDTO dto){

        return new Listing (dto.getAvailableStartDate(), dto.getAvailableEndDate());
    }
}
