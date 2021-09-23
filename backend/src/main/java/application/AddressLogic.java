package application;

import entityDO.Address;
import repositories.AddressRepository;

public class AddressLogic {

    AddressRepository addressRepository;

    public AddressLogic(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressLogic() {
    }

    public Address createNewAddress(Address address){
        return addressRepository.addAddress(address);
    }
}
