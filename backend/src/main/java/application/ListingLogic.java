package application;

import com.mongodb.client.MongoCollection;
import dtos.*;
import entityDO.Image;
import entityDO.Listing;
import entityDO.User;
import mapper.ListingMapper;
import org.bson.Document;
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
    FilteredListingDTO filteredListingDTO;
    ListingRevisionRepository listingRevisionRepository;
    MongoCollection collection;
    LogicHandler logicHandler;
    private ListingMapper ls;
    private List<SingeListingDTO> oldVersionListing;


    public ListingLogic() {
    }

    public ListingLogic( MongoCollection collection, Repositories repositories, LogicHandler logicHandler) {
        this.listingRepository = repositories.listingRepository;
        this.bookingRepository = repositories.bookingRepository;
        this.listingRevisionRepository = repositories.listingRevisionRepository;
        this.repositories = repositories;
        this.ls= new ListingMapper();
        this.collection = collection;
        this.logicHandler = logicHandler;
    }

    public Listing createNewListing(AddListingDTO dto, User user) {
        Listing newListing = ls.convertAddListingToListing(dto, user);
        return listingRepository.addListing(newListing);
    }

    public List<FilteredListingDTO> getAllListingsDTO(){

            List<Listing> allListings = listingRepository.findAllListings();
            List<FilteredListingDTO> allListingsDTO = new ArrayList<>();
            for ( Listing l: allListings
            ) {
                filteredListingDTO = ls.convertListingToFilteredDTO(l);
                allListingsDTO.add(filteredListingDTO);
            }
            return allListingsDTO;
    }

    public List<FilteredListingDTO> getFilteredListings(ListingFilterDTO filter){
        List<Listing>matchedListing = listingRepository.filterListing(filter);
        List<FilteredListingDTO> matchedListingDTO = new ArrayList<>();

        for (Listing l: matchedListing
             ) {
            if(!bookingRepository.checkIfListingIsAlreadyBooked(filter.getAvailableStartDate(), filter.getAvailableEndDate(), l)){
                filteredListingDTO = ls.convertListingToFilteredDTO(l);
                matchedListingDTO.add(filteredListingDTO);
            }
        }

        return matchedListingDTO;
    }

    public List<GetAllListingsInSummaryFromUserDTO> getAllListingsInSummaryFromUser(int userID){

        User user = repositories.getUserRepository().findUserById(userID);

        System.out.println(user.getFirstName());

        List <Listing> listingList = listingRepository.findAllListingsFromUser(user.getId());
        if(listingList == null){
            System.out.println("Returned 0");
            return null;
        }

        ArrayList<GetAllListingsInSummaryFromUserDTO> allListingsDTO = new ArrayList<>();
        listingList.forEach((listing) ->{
            allListingsDTO.add(new GetAllListingsInSummaryFromUserDTO(listing.getId(), listing.getPrice(), listing.getDescription()));
        });

        return allListingsDTO;

    }

    public Listing updateListing(Listing listing){
        Listing oldList = listingRepository.findById(listing.getId()).get();

        if(listing.getPrice() == 0){
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

    public SingeListingDTO getSingleListing(int id){
        Listing l = listingRepository.findById(id).get();

        ArrayList<String> imagelist = new ArrayList<>();

        if(!l.getImages().isEmpty() ){
            for (Image image:l.getImages()
            ) {
                imagelist.add(image.getImageName());
            }
        }


        return new SingeListingDTO(l.getId (),(int) Math.ceil(l.getPrice()*1.15),l.getDescription(), l.getAvailableStartDate(), l.getAvailableEndDate(),
                l.getAddress().getCity(),l.getAddress().getAddressListing(), l.getAmenities().getBathTub(),
                l.getAmenities().getParkingLot(), l.getAmenities().getStove(),l.getAmenities().getDoubleBed(),
                l.getAmenities().getBubblePool(), l.getAmenities().getBicycle(), l.getAmenities().getSauna(),imagelist);
    }

    public List<SingeListingDTO> getoldVersion(int id) {
        oldVersionListing = new ArrayList<>();

        List<ListingRevision> l = listingRevisionRepository.findAllListingRevisionsByListingID(id);
        for (ListingRevision ll : l) {
            oldVersionListing.add(new SingeListingDTO(ll.getId(),(int) Math.ceil(ll.getPrice()*1.15), ll.getDescription(), ll.getAvailableStartDate(), ll.getAvailableEndDate(),
                    ll.getAddressRevision().getAddress(), ll.getAddressRevision().getAddress(), ll.getAmenitiesRevsion().getBathTub(),
                    ll.getAmenitiesRevsion().getParkingLot(), ll.getAmenitiesRevsion().getStove(), ll.getAmenitiesRevsion().getDoubleBed(),
                    ll.getAmenitiesRevsion().getBubblePool(), ll.getAmenitiesRevsion().getBicycle(), ll.getAmenitiesRevsion().getSauna()));
        }
        return oldVersionListing;
    }

    public Document getAllListFromMongoDB(MongoCollection collection){
        return repositories.mongoDBRepository.getAllListingFromMDB(collection);
    }

    public void updateListingAndCreateANewMongoDBCollection(Listing listing){
        repositories.listingRepository.updateListing(listing);
        logicHandler.cacheLogic.updateMongoDBFromSql();
    }




}
