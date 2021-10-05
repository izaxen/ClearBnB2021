package routes;

import application.AddressLogic;
import application.LogicHandler;
import application.Repositories;
import dtos.AddAddressDTO;
import dtos.UpdateAddressDTO;
import entityDO.Listing;
import express.Express;
import mapper.AddressMapper;

public class AddressRoutes {

    private AddressMapper as;


    public AddressRoutes(Express app, LogicHandler logicHandler) {
        as = new AddressMapper();

        app.post("/api/address", (req, res) -> {
            Listing currentListing = req.session("current-Listing");
            res.json(logicHandler.getAddressLogic().createNewAddress((req.body(
                    AddAddressDTO.class)), currentListing));
        });

        app.put("/api/address/", (req, res) -> {
            Listing currentListing = req.session("current-Listing");
            Listing list = logicHandler.getAddressLogic().updateAddress(
                    as.convertUpdateAddressToAddress(req.body(
                            UpdateAddressDTO.class), currentListing),currentListing
            );
            System.out.println("in router: " + list);
        });


    }
}
