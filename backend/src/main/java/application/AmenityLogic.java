package application;

import dtos.AddAmenityDTO;
import entityDO.Amenities;
import entityDO.AmenitiesRevision;
import entityDO.Listing;
import entityDO.ListingRevision;
import repositories.AmenitiesRepository;
import repositories.AmenitiesRevisionRepository;
import repositories.ListingRepository;
import repositories.ListingRevisionRepository;
import mapper.AmenityService;

public class AmenityLogic {
    ListingRepository lR;
    AmenitiesRepository amenitiesRepository;
    AmenitiesRevisionRepository amenitiesRevisionRepository;
    ListingRevisionRepository listingRevisionRepository;
    AmenityService as;

    public AmenityLogic(Repositories repos) {

        this.amenitiesRepository = repos.amenitiesRepository;
        this.amenitiesRevisionRepository = repos.amenitiesRevisionRepository;
        this.listingRevisionRepository = repos.listingRevisionRepository;
        this.lR = repos.listingRepository;
        this.as = new AmenityService();
    }

    public AmenityLogic() {
    }

    public Listing createNewAmenity(AddAmenityDTO dto, Listing listing){
        Amenities amenities = as.convertAddAmenitiesToAmenities(dto, listing);
        listing.setAmenities(amenities);
        return listing;
    }

    public Listing updateAmenties(Amenities ama, Listing listing){
        Amenities oldList = amenitiesRepository.findById(ama.getId()).get();
        //Listing listing = repositories.listingRepository.findById(ama.getId()).get();

        if(ama.getBathTub()== null){
            ama.setBathTub(oldList.getBathTub());
        }
        if(ama.getBubblePool() == null){
            ama.setBubblePool(oldList.getBubblePool());
        }
        if(ama.getBicycle() == null){
            ama.setBicycle(oldList.getBicycle());
        }
        if(ama.getDoubleBed() == null){
            ama.setDoubleBed(oldList.getDoubleBed());
        }
        if(ama.getParkingLot() == null){
            ama.setParkingLot(oldList.getParkingLot());
        }
        if(ama.getSauna() == null){
            ama.setSauna(oldList.getSauna());
        }
        if(ama.getStove() == null){
            ama.setStove(oldList.getStove());
        }

        createAmenitiesVersionBackup(oldList);
        listing.setAmenities(ama);
        return listing;
        //return lR.updateListing(listing);
    }
        private void createAmenitiesVersionBackup(Amenities oldList){

        ListingRevision lr = listingRevisionRepository.findRevIDByID(oldList.getId());

            AmenitiesRevision copyOldAmenitiesList = new AmenitiesRevision(oldList.getBathTub(),
                    oldList.getParkingLot(), oldList.getStove(), oldList.getDoubleBed(), oldList.getBubblePool(),
                    oldList.getBicycle(), oldList.getSauna(), lr);
            lr.setAmenitiesRevsion(copyOldAmenitiesList);
            listingRevisionRepository.updateListingRevision(lr);

        }


}
