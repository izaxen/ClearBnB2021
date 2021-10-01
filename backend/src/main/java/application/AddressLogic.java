package application;

import dtos.AddAddressDTO;
import entityDO.*;
import repositories.AddressRepository;
import repositories.AddressRevisionRepository;
import repositories.ListingRepository;
import repositories.ListingRevisionRepository;
import mapper.AddressService;

public class AddressLogic {

    AddressRepository addressRepository;
    ListingRepository lR;
    AddressRevisionRepository addressRevisionRepository;
    ListingRevisionRepository listingRevisionRepository;
    AddressService as;
    Repositories repositories;

    public AddressLogic(Repositories repo) {
        this.addressRepository = repo.addressRepository;
        this.addressRevisionRepository = repo.addressRevisionRepository;
        this.listingRevisionRepository = repo.listingRevisionRepository;
        this.lR= repo.listingRepository;
        this.as = new AddressService();
        this.repositories = new Repositories();
    }

    public AddressLogic() {
    }

    public Listing createNewAddress(AddAddressDTO dto, Listing listing){

        Address address = as.convertAddAddressToAddress(dto, listing);
        listing.setAddress(address);
        return lR.updateListing(listing); //addressRepository.addAddress(address);
    }

    public Address updateAddress(Address adds){
        Address oldlist = addressRepository.findById(adds.getId()).get();

        if(adds.getCity()==null){
            adds.setCity(oldlist.getCity());
        }
        if(adds.getAddressListing() == null){
            adds.setAddressListing(oldlist.getAddressListing());
        }
        System.out.println("adds: " + adds);
        createAddressVersionBackup(oldlist);

        return addressRepository.updateAddress(adds);

    }

    private void createAddressVersionBackup(Address oldlist){
        ListingRevision lr = listingRevisionRepository.findRevIDByID(oldlist.getId());

        AddressRevision copyOldAddressList = new AddressRevision(lr, oldlist.getCity(), oldlist.getAddressListing());
        addressRevisionRepository.addAddressRevision(copyOldAddressList);

    }
}
