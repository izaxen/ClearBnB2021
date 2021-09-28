package mapper;

import application.Repositories;
import dtos.AddAddressDTO;
import dtos.UpdateAddressDTO;
import entityDO.Address;
import entityDO.Listing;


public class AddressService {
    Repositories lR;
    Listing listing;


    public Address convertAddAddressToAddress(AddAddressDTO dto, Listing listing) {

        return new Address(dto.getAddress(), dto.getCity(),listing);
    }

    public Address convertUpdateAddressToAddress(UpdateAddressDTO dto, Listing listing){

        return new Address(dto.getId(), dto.getAddress(), dto.getCity(),listing);

    }
}
