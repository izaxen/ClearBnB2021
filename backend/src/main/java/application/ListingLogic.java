package application;

import entityDO.Listing;
import org.hibernate.Filter;
import org.hibernate.Session;
import repositories.ListingRepository;

import java.sql.Timestamp;
import java.util.List;

public class ListingLogic {

    ListingRepository listingRepository;
    Repositories rp;

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
        rp = new Repositories();
        Session session = rp.entityManager.unwrap(Session.class);

        // Filter does not run condition?
        // If wrong name on filter, system crashes, which means we have connected to our filter
        // But why it oe
        Filter availableDateFilter = session.enableFilter("dateFilter");
        System.out.println(session.getEnabledFilter("dateFilter"));

        Timestamp ts1 = listing.getAvailableStartDate();
        Timestamp ts2 = listing.getAvailableEndDate();

//        ts1 = new Timestamp(163239371);

        availableDateFilter.setParameter("availableStartDate", ts1);
        availableDateFilter.setParameter("availableEndDate", ts2);

        System.out.println("ts1: " + ts1);
        System.out.println("ts2: " + ts2);

//        List<Listing> matchedListing = listingRepository.findAllListings();


        List<Listing> matchedListing = session.createQuery("FROM Listing as l WHERE l.availableStartDate <= :selectedStartDate and l.availableEndDate >= :selectedEndDate", Listing.class)
                .setParameter("selectedStartDate", ts1)
                .setParameter("selectedEndDate", ts2)
                .list();

        System.out.println("matchedLsiting: " + matchedListing);

        return matchedListing;
    }

}
