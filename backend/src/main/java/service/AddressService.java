package service;

import application.Repositories;
import dtos.AddAddressDTO;
import dtos.AddAmenityDTO;
import dtos.AddListingDTO;
import entityDO.Address;
import entityDO.Listing;
import entityDO.User;
import repositories.ListingRepository;


public class AddressService {
    Repositories lR;
    Listing listing;

    public Address convertAddAddressToAddress(AddAddressDTO dto, Listing listing) {

        return new Address(dto.getAddress(), dto.getCity(), listing);
    }
}
