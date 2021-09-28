package application;

import dtos.AddListingDTO;
import dtos.GetAllListingsInSummaryFromUserDTO;
import dtos.ListingFilterDTO;
import entityDO.Listing;
import entityDO.User;
import org.hibernate.Session;
import repositories.BookingRepository;
import repositories.ListingRepository;
import repositories.ListingRevisionRepository;
import entityDO.ListingRevision;

import java.util.ArrayList;
import java.util.List;

public class ListingLogic {

    ListingRepository listingRepository;
    BookingRepository bookingRepository;
    Repositories repositories;
    AddListingDTO addListingDTO;
    AddListingDTO addListingDTOForBooking;
    ListingRevisionRepository listingRevisionRepository;

    public ListingLogic(Repositories repositories) {
        this.listingRepository = repositories.listingRepository;
        this.bookingRepository = repositories.bookingRepository;
         this.listingRevisionRepository = repositories.listingRevisionRepository;
        this.repositories = repositories;
    }

    public ListingLogic() {
    }

    public Listing createNewListing(Listing listing) {
        return listingRepository.addListing(listing);
    }

//    public List<Listing> getAllListings(){
//        return listingRepository.findAllListings();}

    public List<AddListingDTO> getAllListings(){
        List<Listing> allListings = listingRepository.findAllListings();
        List<AddListingDTO> allListingsDTOForBooking = new ArrayList<>();
        for ( Listing l: allListings
        ) {
            addListingDTOForBooking = new AddListingDTO(l);
            allListingsDTOForBooking.add(addListingDTOForBooking);
        }
        return allListingsDTOForBooking;
    }

    public List<AddListingDTO> getAllListingsDTO(){

            List<Listing> allListings = listingRepository.findAllListings();
            List<AddListingDTO> allListingsDTO = new ArrayList<>();
            for ( Listing l: allListings
            ) {
                addListingDTO = new AddListingDTO(l);
                allListingsDTO.add(addListingDTO);
            }
            return allListingsDTO;
    }

    public List<AddListingDTO> getFilteredListings(ListingFilterDTO filter){
        // time should send as argument
        Session session = repositories.entityManager.unwrap(Session.class);

        // Filter does not run condition?
        // If wrong name on filter, system crashes, which means we have connected to our filter
        // But why it oe
//        Filter availableDateFilter = session.enableFilter("dateFilter");
//        System.out.println(session.getEnabledFilter("dateFilter"));

        String ts1 = filter.getAvailableStartDate();
        String ts2 = filter.getAvailableEndDate();
        Boolean isBathTub = filter.isBathTub();
        Boolean isParkingLot = filter.isParkingLot();
        Boolean isStove = filter.isStove();
        Boolean isDoubleBed = filter.isDoubleBed();
        Boolean isBubblePool = filter.isBubblePool();
        Boolean isCycle = filter.isCycle();
        Boolean isSauna = filter.isSauna();
        int maxPrice = filter.getPrice();

//        System.out.println("ts1: " + ts1);
//        System.out.println("ts2: " + ts2);
//        System.out.println("isBathTub: " + isBathTub);
//        System.out.println("isParkingLot: " + isParkingLot);
//        System.out.println("isStove: " + isStove);
//        System.out.println("isDoubleBed: " + isDoubleBed);
//        System.out.println("isBubblePool: " + isBubblePool);
//        System.out.println("isCycle: " + isCycle);
//        System.out.println("isSauna: " + isSauna);
//        System.out.println("MAX price: " + maxPrice);


        // Though this works, we don't want to return the whole list of Listing back to
        // front page, because this would send User info with it, DTO it!

        List<Listing> matchedListing = session.createQuery("FROM Listing as l WHERE " +
                "(:selectedStartDate IS NULL or l.availableStartDate <= :selectedStartDate) AND " +
                "(:selectedEndDate IS NULL or l.availableEndDate >= :selectedEndDate) AND " +
                        "(:isBathTub IS FALSE or l.amenities.isBathTub IS :isBathTub) AND " +
                        "(:isParkingLot IS FALSE or l.amenities.isParkingLot IS :isParkingLot) AND " +
                        "(:isStove IS FALSE or l.amenities.isStove IS :isStove) AND " +
                        "(:isDoubleBed IS FALSE or l.amenities.isDoubleBed IS :isDoubleBed) AND " +
                        "(:isBubblePool IS FALSE or l.amenities.isBubblePool IS :isBubblePool) AND " +
                        "(:isCycle IS FALSE or l.amenities.isCycle IS :isCycle) AND " +
                        "(:isSauna IS FALSE or l.amenities.isSauna IS :isSauna) AND " +
                        "(:maxPrice = 0 or l.price <= :maxPrice)", Listing.class)
                .setParameter("selectedStartDate", ts1)
                .setParameter("selectedEndDate", ts2)
                .setParameter("isBathTub", isBathTub)
                .setParameter("isParkingLot", isParkingLot)
                .setParameter("isStove", isStove)
                .setParameter("isDoubleBed", isDoubleBed)
                .setParameter("isBubblePool", isBubblePool)
                .setParameter("isCycle", isCycle)
                .setParameter("isSauna", isSauna)
                .setParameter("maxPrice", maxPrice)
                .list();



//        for (Object l : matchedListing
//             ) {
//            System.out.println( l);
//        }

        List<AddListingDTO> matchedListingDTO = new ArrayList<>();

        for (Listing l: matchedListing
             ) {

            // if false, add it to filteredListing to render out.
            if(!bookingRepository.checkIfListingIsAlreadyBooked(ts1, ts2, l)){
                addListingDTO = new AddListingDTO(l);
                matchedListingDTO.add(addListingDTO);
            }

        }

        return matchedListingDTO;
    }

    public List<GetAllListingsInSummaryFromUserDTO> getAllListingsInSummaryFromUser(int userID){

        User user = repositories.getUserRep().findUserById(userID);

        List <Listing> listingList = listingRepository.findAllListingsFromUser(user);

        ArrayList<GetAllListingsInSummaryFromUserDTO> allListingsDTO = new ArrayList<>();
        listingList.forEach((listing) ->{
            allListingsDTO.add(new GetAllListingsInSummaryFromUserDTO(listing.getId(), listing.getPrice(), listing.getDescription()));
        });


        return allListingsDTO;

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

        createListingVersionBackup(oldList);

        return listingRepository.updateListing(listing);
    }

    private ListingRevision createListingVersionBackup(Listing oldList){
        ListingRevision copyOldList = new ListingRevision(oldList.getPrice(), oldList.getDescription(),
                oldList.getAvailableStartDate(), oldList.getAvailableEndDate(), oldList, oldList.getUser());
        listingRevisionRepository.addListingRevision(copyOldList);

        return copyOldList;



    }
}
