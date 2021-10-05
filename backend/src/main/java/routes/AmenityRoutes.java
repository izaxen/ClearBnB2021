package routes;

import application.LogicHandler;
import dtos.AddAmenityDTO;
import dtos.UpdateAmenityDTO;
import entityDO.Listing;
import express.Express;
import mapper.AmenityMapper;

public class AmenityRoutes {

    private AmenityMapper ams;
    private Express app;
    private LogicHandler logicHandler;

    public AmenityRoutes(Express app, LogicHandler logicHandler) {
        ams = new AmenityMapper();
        this.app = app;
        this.logicHandler = logicHandler;
        addAmenity();
        updateAmenity();

    }

    public void addAmenity() {
        app.post("api/amenity", (req, res) -> {
            Listing currentListing = req.session("current-Listing");
            res.json(logicHandler.getAmenityLogic().createNewAmenity(req.body(AddAmenityDTO.class), currentListing));
        });
    }

    public void updateAmenity() {
        app.put("api/amenity", (req, res) -> {
            Listing currentListing = req.session("current-Listing");
            logicHandler.getAmenityLogic().updateAmenities((
                            req.body(UpdateAmenityDTO.class)), currentListing);

        });
    }

}
