package routes;

import application.AmenityLogic;
import application.Repositories;
import dtos.AddAmenityDTO;
import dtos.UpdateAmenityDTO;
import entityDO.Listing;
import express.Express;
import entityDO.Amenities;
import mapper.AmenityService;

public class AmenityRoutes {

    private AmenityLogic amenityLogic;
    private AmenityService ams;

    public AmenityRoutes(Express app, Repositories repos) {
        amenityLogic = new AmenityLogic(repos);
        ams = new AmenityService();

        app.post("api/amenity",(req, res) -> {
            Listing currentListing = req.session("current-Listing");
            res.json(amenityLogic.createNewAmenity(req.body(AddAmenityDTO.class), currentListing));
        });

        app.put("api/amenity",(req, res) -> {   //update amenity
            Listing currentListing = req.session("current-Listing");
            Listing list = amenityLogic.updateAmenties(
                    ams.convertUpdateAmenitiesToAmenities(
                            req.body(UpdateAmenityDTO.class), currentListing),currentListing);

            //res.json(;
        });

    }


}
