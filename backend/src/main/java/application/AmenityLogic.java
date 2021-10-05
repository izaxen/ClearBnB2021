package application;

import dtos.AddAmenityDTO;
import dtos.UpdateAmenityDTO;
import entityDO.Amenities;
import entityDO.AmenitiesRevision;
import entityDO.Listing;
import entityDO.ListingRevision;
import repositories.AmenitiesRepository;
import repositories.AmenitiesRevisionRepository;
import repositories.ListingRepository;
import repositories.ListingRevisionRepository;
import mapper.AmenityMapper;

public class AmenityLogic {
    ListingRepository lR;
    AmenitiesRepository amenitiesRepository;
    AmenitiesRevisionRepository amenitiesRevisionRepository;
    ListingRevisionRepository listingRevisionRepository;
    AmenityMapper amper;

    public AmenityLogic(Repositories repos) {

        this.amenitiesRepository = repos.amenitiesRepository;
        this.amenitiesRevisionRepository = repos.amenitiesRevisionRepository;
        this.listingRevisionRepository = repos.listingRevisionRepository;
        this.lR = repos.listingRepository;
        this.amper = new AmenityMapper();
    }

    public AmenityLogic() {
    }

    public Listing createNewAmenity(AddAmenityDTO dto, Listing listing){
        Amenities amenities = amper.convertAddAmenitiesToAmenities(dto, listing);
        listing.setAmenities(amenities);
        return listing;
    }

    public Listing updateAmenities(UpdateAmenityDTO dto, Listing listing){
        Amenities oldList = amenitiesRepository.findById(dto.getId()).get();
        Amenities ama = amper.convertUpdateAmenitiesToAmenities(dto, listing);

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
