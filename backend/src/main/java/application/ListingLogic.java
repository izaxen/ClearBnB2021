package application;

import entityDO.Listing;
import repositories.ListingRepository;
import java.util.List;

public class ListingLogic {

    ListingRepository listingRepository;

    public ListingLogic(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
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

        return listingRepository.updateListing(listing);
    }
}
