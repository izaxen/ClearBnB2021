package routes;

import application.AmenityLogic;
import express.Express;
import entityDO.Amenities;
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
