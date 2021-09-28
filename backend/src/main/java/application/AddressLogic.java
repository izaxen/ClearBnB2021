package application;

import dtos.AddAddressDTO;
import entityDO.*;
import repositories.AddressRepository;
import repositories.AddressRevisionRepository;
import repositories.ListingRevisionRepository;
import mapper.AddressService;

public class AddressLogic {

    AddressRepository addressRepository;
    AddressRevisionRepository addressRevisionRepository;
    ListingRevisionRepository listingRevisionRepository;
    AddressService as;

    public AddressLogic(Repositories repo) {
        this.addressRepository = repo.addressRepository;
        this.addressRevisionRepository = repo.addressRevisionRepository;
        this.listingRevisionRepository = repo.listingRevisionRepository;
        this.as = new AddressService();
    }

    public AddressLogic() {
    }

    public Address createNewAddress(AddAddressDTO dto, Listing listing){

        Address address = as.convertAddAddressToAddress(dto, listing);
        return addressRepository.addAddress(address);
    }

    public Address updateAddress(Address adds){
        Address oldlist = addressRepository.findById(adds.getListing().getId()).get();

        if(adds.getCity()==null){
            adds.setCity(oldlist.getCity());
        }
        if(adds.getAddressListing() == null){
            adds.setAddressListing(oldlist.getAddressListing());
        }
        createAddressVersionBackup(oldlist);

        return addressRepository.updateAddress(adds);

    }

    private void createAddressVersionBackup(Address oldlist){
        ListingRevision lr = listingRevisionRepository.findRevIDByID(oldlist.getId());

        AddressRevision copyOldAddressList = new AddressRevision(lr, oldlist.getCity(), oldlist.getAddressListing());
        addressRevisionRepository.addAddressRevision(copyOldAddressList);

    }
}
