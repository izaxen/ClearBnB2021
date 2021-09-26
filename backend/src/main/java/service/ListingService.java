package service;

import dtos.AddListingDTO;
import dtos.FilteredListingDTO;
import entityDO.Listing;
import entityDO.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Filter;

public class ListingService {

    // this converts DTO into a fake
    public Listing convertAddListingToListing(AddListingDTO dto, User owner) {
        return new Listing(dto.getPrice(), dto.getDescription(), dto.getAvailableStartDate(), dto.getAvailableEndDate(), owner);
    }

    public Listing convertToFilteredListingsDTO(FilteredListingDTO dto){

        return new Listing (dto.getAvailableStartDate(), dto.getAvailableEndDate());
    }
}
