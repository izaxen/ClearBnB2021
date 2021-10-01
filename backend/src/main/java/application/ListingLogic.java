package application;

import dtos.AddListingDTO;
import dtos.FilteredListingDTO;
import dtos.GetAllListingsInSummaryFromUserDTO;
import dtos.ListingFilterDTO;
import entityDO.Listing;
import entityDO.User;
import mapper.ListingService;
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
    AddListingDTO addListingDTOForBooking;
    ListingRevisionRepository listingRevisionRepository;
    private ListingService ls;

    public ListingLogic(Repositories repositories) {
        this.listingRepository = repositories.listingRepository;
        this.bookingRepository = repositories.bookingRepository;
         this.listingRevisionRepository = repositories.listingRevisionRepository;
        this.repositories = repositories;
        this.ls= new ListingService();
    }

    public ListingLogic() {
    }

    public Listing createNewListing(AddListingDTO dto, User user) {
        Listing newListing = ls.convertAddListingToListing(dto, user);
        return listingRepository.addListing(newListing);
    }

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

        User user = repositories.getUserRep().findUserById(userID);

        List <Listing> listingList = listingRepository.findAllListingsFromUser(user);
        if(listingList.size() == 0){
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
