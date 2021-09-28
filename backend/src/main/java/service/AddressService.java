package service;

import application.Repositories;
import dtos.AddAddressDTO;
import dtos.AddAmenityDTO;
import dtos.AddListingDTO;
import dtos.UpdateAddressDTO;
import entityDO.Address;
import entityDO.Listing;
import entityDO.User;
import repositories.ListingRepository;
import repositories.ListingRevisionRepository;


public class AddressService {


    public Address convertAddAddressToAddress(AddAddressDTO dto, Listing listing) {

        return new Address(dto.getAddress(), dto.getCity(),listing);
    }

    public Address convertUpdateAddressToAddress(UpdateAddressDTO dto, Listing listing){

        return new Address(dto.getId(), dto.getAddress(), dto.getCity(),listing);

    }
}
