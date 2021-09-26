package routes;

import application.AmenityLogic;
import dtos.AddAmenityDTO;
import entityDO.Listing;
import express.Express;
import entityDO.Amenities;
import repositories.AmenitiesRepository;
import service.AmenityService;

public class AmenityRoutes {

    private AmenityLogic amenityLogic;
    private AmenityService ams;

    public AmenityRoutes(Express app, AmenitiesRepository amenitiesRepository) {
        amenityLogic = new AmenityLogic(amenitiesRepository);
        ams = new AmenityService();
        app.post("api/addAmenity",(req, res) -> {   //Create amenity
            Listing currentListing = req.session("current-Listing");
            Amenities amenity = amenityLogic.createNewAmenity(
                    ams.convertAddAmenitiesToAmenities(
                            req.body(AddAmenityDTO.class), currentListing));
            res.json(amenity);
        });

        app.post("api/updateAmenity",(req, res) -> {   //Create amenity
            Listing currentListing = req.session("current-rev-list");

            Amenities amenity = amenityLogic.updateAmenties(
                    ams.convertAddAmenitiesToAmenities(
                            req.body(AddAmenityDTO.class), currentListing));

            res.json(amenity);
        });

    }


}
