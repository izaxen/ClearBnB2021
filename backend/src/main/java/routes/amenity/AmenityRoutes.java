package routes.amenity;

import application.amenity.AmenityLogic;
import express.Express;
import models.Address;
import models.Amenities;
import repositories.AmenitiesRepository;

public class AmenityRoutes {

    private AmenityLogic amenityLogic;

    public AmenityRoutes(Express app, AmenitiesRepository amenitiesRepository) {

        amenityLogic = new AmenityLogic(amenitiesRepository);

        app.post("api/addAmenity",(req, res) -> {   //Create amenity
            Amenities amenity = amenityLogic.createNewAmenity(req.body(Amenities.class));
            res.json(amenity);
        });

    }


}
