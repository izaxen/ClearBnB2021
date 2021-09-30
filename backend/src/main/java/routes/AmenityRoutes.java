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

        app.post("api/addAmenity",(req, res) -> {
            Listing currentListing = req.session("current-Listing");
            res.json(amenityLogic.createNewAmenity(req.body(AddAmenityDTO.class), currentListing));
        });

        app.post("api/updateAmenity",(req, res) -> {   //update amenity
            Listing currentListing = req.session("current-Listing");
            System.out.println(req.body(UpdateAmenityDTO.class).toString());

            Amenities amenity = amenityLogic.updateAmenties(
                    ams.convertUpdateAmenitiesToAmenities(
                            req.body(UpdateAmenityDTO.class), currentListing));

            res.json(amenity);
        });

    }


}
