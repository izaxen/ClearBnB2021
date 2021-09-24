package application;

import dtos.AddListingDTO;
import dtos.FilteredListingDTO;
import entityDO.Listing;
import entityDO.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;
import repositories.ListingRepository;
import service.ListingService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ListingLogic {

    ListingRepository listingRepository;
    private EntityManager entityManager;
    private ListingService ls;

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


    public List<Listing> getFilteredListings(Listing listing){
        // time should send as argument

        Session session = entityManager.unwrap(Session.class);
        Filter availableDateFilter = session.enableFilter("availableDateFilter");

        Timestamp ts1 = listing.getAvailableStartDate();
        Timestamp ts2 = listing.getAvailableEndDate();

        availableDateFilter.setParameter("availableStartDate", ts1);
        availableDateFilter.setParameter("availableEndDate", ts2);

        List<Listing> matchedListing = listingRepository.findAllListings();

        return matchedListing;
    }

}
