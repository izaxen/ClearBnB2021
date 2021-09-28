package application;

import dtos.AddAmenityDTO;
import entityDO.Amenities;
import entityDO.AmenitiesRevision;
import entityDO.Listing;
import entityDO.ListingRevision;
import repositories.AmenitiesRepository;
import repositories.AmenitiesRevisionRepository;
import repositories.ListingRevisionRepository;
import mapper.AmenityService;

public class AmenityLogic {

    AmenitiesRepository amenitiesRepository;
    AmenitiesRevisionRepository amenitiesRevisionRepository;
    ListingRevisionRepository listingRevisionRepository;
    AmenityService as;

    public AmenityLogic(Repositories repos) {

        this.amenitiesRepository = repos.amenitiesRepository;
        this.amenitiesRevisionRepository = repos.amenitiesRevisionRepository;
        this.listingRevisionRepository = repos.listingRevisionRepository;
        this.as = new AmenityService();
    }

    public AmenityLogic() {
    }

    public Amenities createNewAmenity(AddAmenityDTO dto, Listing listing){
        Amenities amenities = as.convertAddAmenitiesToAmenities(dto, listing);
        return amenitiesRepository.addAmenities(amenities);
    }

    public Amenities updateAmenties(Amenities ama){
        Amenities oldList = amenitiesRepository.findById(ama.getListing().getId()).get();

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

        return amenitiesRepository.updateAmenities(ama);
    }
        private void createAmenitiesVersionBackup(Amenities oldList){
        ListingRevision lr = listingRevisionRepository.findRevIDByID(oldList.getId());

            AmenitiesRevision copyOldAmenitiesList = new AmenitiesRevision(lr,oldList.getBathTub(),
                    oldList.getParkingLot(), oldList.getStove(), oldList.getDoubleBed(), oldList.getBubblePool(),
                    oldList.getBicycle(), oldList.getSauna());
            amenitiesRevisionRepository.addAmenitiesRevision(copyOldAmenitiesList);

        }


}
