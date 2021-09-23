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
}
