package application;

import dtos.getAllListingsInSummaryFromUserDTO;
import entityDO.Listing;
import entityDO.User;
import repositories.ListingRepository;

import java.util.List;

public class ListingLogic {

    ListingRepository listingRepository;
    Repositories repositories;

    public ListingLogic(Repositories repositories) {
        this.listingRepository = repositories.listingRepository;
        this.repositories = repositories;
    }

    public ListingLogic() {
    }

    public Listing createNewListing(Listing listing) {
        return listingRepository.addListing(listing);
    }

    public List<Listing> getAllListings(){
        return listingRepository.findAllListings();
    }

   /* public List<getAllListingsInSummaryFromUserDTO> getAllListingsInSummaryFromUser(int userID){
        User user = repositories.getUserRep().findUserById(userID);

        return listingRepository.findAllListingsFromUser(user);
    }*/



}
