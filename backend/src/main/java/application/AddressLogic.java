package application;

import entityDO.Address;
import entityDO.AddressRevision;
import entityDO.ListingRevision;
import repositories.AddressRepository;
import repositories.AddressRevisionRepository;
import repositories.ListingRevisionRepository;

public class AddressLogic {

    AddressRepository addressRepository;
    AddressRevisionRepository addressRevisionRepository;
    ListingRevisionRepository listingRevisionRepository;

    public AddressLogic(Repositories repo) {
        this.addressRepository = repo.addressRepository;
        this.addressRevisionRepository = repo.addressRevisionRepository;
        this.listingRevisionRepository = repo.listingRevisionRepository;
    }

    public AddressLogic() {
    }

    private Address createNewAddress(Address address){

        return addressRepository.addAddress(address);
    }

    private Address updateAddress(Address adds){
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
