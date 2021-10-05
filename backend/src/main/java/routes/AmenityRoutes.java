package routes;

import application.LogicHandler;
import dtos.AddAmenityDTO;
import dtos.UpdateAmenityDTO;
import entityDO.Listing;
import express.Express;
import mapper.AmenityMapper;

public class AmenityRoutes {

    private AmenityMapper ams;

    public AmenityRoutes(Express app, LogicHandler logicHandler) {
        ams = new AmenityMapper();

        app.post("api/amenity",(req, res) -> {
            Listing currentListing = req.session("current-Listing");
            res.json(logicHandler.getAmenityLogic().createNewAmenity(req.body(AddAmenityDTO.class), currentListing));
        });

        app.put("api/amenity",(req, res) -> {
            Listing currentListing = req.session("current-Listing");
            logicHandler.getAmenityLogic().updateAmenties(
                    ams.convertUpdateAmenitiesToAmenities(
                            req.body(UpdateAmenityDTO.class), currentListing),currentListing);

        });

    }


}
