package application;

import com.mongodb.client.MongoCollection;
import dtos.*;
import entityDO.Image;
import entityDO.Listing;
import entityDO.ListingRevision;
import entityDO.User;
import mapper.ListingMapper;
import org.bson.Document;
import repositories.BookingRepository;
import repositories.ListingRepository;
import repositories.ListingRevisionRepository;

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
    ListingMapper listMap;
    List<SingeListingDTO> oldVersionListing;

    public ListingLogic() {
    }

    public ListingLogic(MongoCollection collection, Repositories repositories, LogicHandler logicHandler) {
        this.listingRepository = repositories.listingRepository;
        this.bookingRepository = repositories.bookingRepository;
        this.listingRevisionRepository = repositories.listingRevisionRepository;
        this.repositories = repositories;
        this.listMap = new ListingMapper();
        this.collection = collection;
        this.logicHandler = logicHandler;
    }

    public Listing createNewListing(AddListingDTO dto, User user) {
        Listing newListing = listMap.convertAddListingToListing(dto, user);
        return listingRepository.addListing(newListing);
    }

    public List<FilteredListingDTO> getAllListingsDTO() {
        List<Listing> allListings = listingRepository.findAllListings();
        List<FilteredListingDTO> allListingsDTO = new ArrayList<>();

        for (Listing list : allListings
        ) {
            filteredListingDTO = listMap.convertListingToFilteredDTO(list);
            allListingsDTO.add(filteredListingDTO);
        }
        return allListingsDTO;
    }

    public List<FilteredListingDTO> getFilteredListings(ListingFilterDTO filter) {
        List<Listing> matchedListing = listingRepository.filterListing(filter);
        List<FilteredListingDTO> matchedListingDTO = new ArrayList<>();

        for (Listing list : matchedListing
        ) {
            if (!bookingRepository.checkIfListingIsAlreadyBooked(filter.getAvailableStartDate(),
                    filter.getAvailableEndDate(), list)) {
                filteredListingDTO = listMap.convertListingToFilteredDTO(list);
                matchedListingDTO.add(filteredListingDTO);
            }
        }
        return matchedListingDTO;
    }

    public List<GetAllListingsInSummaryFromUserDTO> getAllListingsInSummaryFromUser(int userID) {
        User user = repositories.getUserRepository().findUserById(userID);
        List<Listing> listingList = listingRepository.findAllListingsFromUser(user.getId());

        if (listingList == null) {
            System.out.println("Returned 0");
            return null;
        }
        ArrayList<GetAllListingsInSummaryFromUserDTO> allListingsDTO = new ArrayList<>();
        listingList.forEach((listing) -> {
            allListingsDTO.add(new GetAllListingsInSummaryFromUserDTO(listing.getId(), listing.getPrice(),
                    listing.getDescription()));
        });
        return allListingsDTO;
    }

    public Listing updateListing(UpdateListingDTO dto, User user) {
        Listing oldList = listingRepository.findById(dto.getId()).get();
        Listing listing = listMap.convertUpdateListingToListing(dto, user);

        if (listing.getPrice() == 0) {
            listing.setPrice(oldList.getPrice());
        }
        if (listing.getDescription() == (null)) {
            listing.setDescription(oldList.getDescription());
        }
        if (listing.getAvailableStartDate() == (null)) {
            listing.setAvailableStartDate(oldList.getAvailableStartDate());
        }
        if (listing.getAvailableEndDate() == (null)) {
            listing.setAvailableEndDate(oldList.getAvailableEndDate());
        }

        createListingVersionBackup(oldList);

        return listingRepository.updateListing(listing);
    }

    private void createListingVersionBackup(Listing oldList) {
        ListingRevision copyOldList = new ListingRevision(oldList.getPrice(), oldList.getDescription(),
                oldList.getAvailableStartDate(), oldList.getAvailableEndDate(), oldList, oldList.getUser());
        listingRevisionRepository.addListingRevision(copyOldList);
    }

    public SingeListingDTO getSingleListing(int id) {
        Listing list = listingRepository.findById(id).get();

        ArrayList<String> imagelist = new ArrayList<>();

        if (!list.getImages().isEmpty()) {
            for (Image image : list.getImages()
            ) {
                imagelist.add(image.getImageName());
            }
        }
        return new SingeListingDTO(list.getId(), (int) Math.ceil(list.getPrice() * 1.15), list.getDescription(),
                list.getAvailableStartDate(), list.getAvailableEndDate(), list.getAddress().getCity(),
                list.getAddress().getAddressListing(), list.getAmenities().getBathTub(), list.getAmenities().getParkingLot(),
                list.getAmenities().getStove(), list.getAmenities().getDoubleBed(), list.getAmenities().getBubblePool(),
                list.getAmenities().getBicycle(), list.getAmenities().getSauna(), imagelist);
    }

    public List<SingeListingDTO> getoldVersion(int id) {
        oldVersionListing = new ArrayList<>();

        List<ListingRevision> list = listingRevisionRepository.findAllListingRevisionsByListingID(id);
        for (ListingRevision item : list) {
            oldVersionListing.add(new SingeListingDTO(item.getId(), (int) Math.ceil(item.getPrice() * 1.15),
                    item.getDescription(), item.getAvailableStartDate(), item.getAvailableEndDate(),
                    item.getAddressRevision().getAddress(), item.getAddressRevision().getAddress(),
                    item.getAmenitiesRevsion().getBathTub(), item.getAmenitiesRevsion().getParkingLot(),
                    item.getAmenitiesRevsion().getStove(), item.getAmenitiesRevsion().getDoubleBed(),
                    item.getAmenitiesRevsion().getBubblePool(), item.getAmenitiesRevsion().getBicycle(),
                    item.getAmenitiesRevsion().getSauna()));
        }
        return oldVersionListing;
    }

    public Document getAllListFromMongoDB(MongoCollection collection) {
        return repositories.mongoDBRepository.getAllListingFromMDB(collection);
    }

    public void updateListingAndCreateANewMongoDBCollection(Listing listing) {
        repositories.listingRepository.updateListing(listing);
        logicHandler.cacheLogic.updateMongoDBFromSql();
    }


}
