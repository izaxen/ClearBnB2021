package service;

import dtos.AddListingDTO;
import entityDO.Listing;
import entityDO.User;

public class ListingService {


    public Listing convertAddListingToListing(AddListingDTO dto, User owner) {
        return new Listing(dto.getPrice(), dto.getDescription(), dto.getAvailableStartDate(), dto.getAvailableEndDate(), owner);
    }
}
