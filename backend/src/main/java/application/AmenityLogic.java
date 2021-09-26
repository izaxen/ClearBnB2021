package application;

import entityDO.Amenities;
import repositories.AmenitiesRepository;

public class AmenityLogic {

    AmenitiesRepository amenitiesRepository;

    public AmenityLogic(AmenitiesRepository amenitiesRepository) {
        this.amenitiesRepository = amenitiesRepository;
    }

    public AmenityLogic() {
    }

    public Amenities createNewAmenity(Amenities amenity){
        return amenitiesRepository.addAmenities(amenity);
    }

    public Amenities updateAmenties(Amenities ama){
        Amenities oldList = amenitiesRepository.findById(ama.getID()).get();

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
        createAmenitiesVersionBackup(Amenities oldList){
        Ame

        }
}
