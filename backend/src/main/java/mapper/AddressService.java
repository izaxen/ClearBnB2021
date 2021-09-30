package mapper;
import dtos.AddAddressDTO;
import dtos.UpdateAddressDTO;
import entityDO.Address;
import entityDO.Listing;


public class AddressService {


    public Address convertAddAddressToAddress(AddAddressDTO dto, Listing listing) {

        return new Address(dto.getAddress(), dto.getCity(),listing);
    }

    public Address convertUpdateAddressToAddress(UpdateAddressDTO dto){

        return new Address(dto.getId(), dto.getAddressListing(), dto.getCity());

    }
}
