package application;

import dtos.AddAddressDTO;
import dtos.UpdateAddressDTO;
import entityDO.*;
import repositories.AddressRepository;
import repositories.AddressRevisionRepository;
import repositories.ListingRepository;
import repositories.ListingRevisionRepository;
import mapper.AddressMapper;

public class AddressLogic {

    AddressRepository addressRepository;
    ListingRepository lR;
    AddressRevisionRepository addressRevisionRepository;
    ListingRevisionRepository listingRevisionRepository;
    AddressMapper addMaps;

    public AddressLogic(Repositories repo) {
        this.addressRepository = repo.addressRepository;
        this.addressRevisionRepository = repo.addressRevisionRepository;
        this.listingRevisionRepository = repo.listingRevisionRepository;
        this.lR= repo.listingRepository;
        this.addMaps = new AddressMapper();
    }

    public AddressLogic() {
    }

    public Listing createNewAddress(AddAddressDTO dto, Listing listing){

        Address address = addMaps.convertAddAddressToAddress(dto, listing);
        listing.setAddress(address);
        return listing;
    }

    public Listing updateAddress(UpdateAddressDTO dto, Listing listing){
        Address adds = addMaps.convertUpdateAddressToAddress(dto, listing);
        Address oldlist = addressRepository.findById(adds.getId()).get();

        if(adds.getCity()==null){
            adds.setCity(oldlist.getCity());
        }
        if(adds.getAddressListing() == null){
            adds.setAddressListing(oldlist.getAddressListing());
        }
        createAddressVersionBackup(oldlist);
        listing.setAddress(adds);
        return listing;
    }

    private void createAddressVersionBackup(Address oldlist){
        ListingRevision lr = listingRevisionRepository.findRevIDByID(oldlist.getId());

        AddressRevision copyOldAddressList = new AddressRevision(oldlist.getCity(), oldlist.getAddressListing(), lr);
        lr.setAddressRevision(copyOldAddressList);
        listingRevisionRepository.updateListingRevision(lr);

    }
}
