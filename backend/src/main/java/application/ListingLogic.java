package application;

import entityDO.Listing;
import entityDO.ListingRevision;
import repositories.ListingRepository;
import repositories.ListingRevisionRepository;

import java.util.List;

public class ListingLogic {

    ListingRepository listingRepository;
    ListingRevisionRepository listingRevisionRepository;

    public ListingLogic(Repositories repos) {

        this.listingRepository = repos.listingRepository;
        this.listingRevisionRepository = repos.listingRevisionRepository;
    }

    public ListingLogic() {
    }

    public Listing createNewListing(Listing listing) {
        return listingRepository.addListing(listing);
    }

    public List<Listing> getAllListings(){
        return listingRepository.findAllListings();
    }

    public Listing updateListing(Listing listing){
        Listing oldList = listingRepository.findById(listing.getId()).get();

        if(listing.getPrice() == null){
            listing.setPrice(oldList.getPrice());
        }
        if(listing.getDescription() ==(null)){
            listing.setDescription(oldList.getDescription());
        }
        if(listing.getAvailableStartDate()==(null)){
            listing.setAvailableStartDate(oldList.getAvailableStartDate());
        }
        if(listing.getAvailableEndDate()==(null)){
            listing.setAvailableEndDate(oldList.getAvailableEndDate());
        }
        if(listing.getUser() ==(null)){
            listing.setUser(oldList.getUser());
        }
        createListingVersionBackup(oldList);

        return listingRepository.updateListing(listing);
    }

    public void createListingVersionBackup(Listing oldList){
        ListingRevision copyOldList = new ListingRevision(oldList.getPrice(), oldList.getDescription(),
                oldList.getAvailableStartDate(), oldList.getAvailableEndDate(), oldList, oldList.getUser());
        listingRevisionRepository.addListingRevision(copyOldList);




    }
}
