package routes;

import application.LogicHandler;
import dtos.AddAddressDTO;
import dtos.UpdateAddressDTO;
import entityDO.Listing;
import express.Express;
import mapper.AddressMapper;

public class AddressRoutes {

    private AddressMapper as;
    private Express app;
    private LogicHandler logicHandler;


    public AddressRoutes(Express app, LogicHandler logicHandler) {
        as = new AddressMapper();
        this.app = app;
        this.logicHandler = logicHandler;
        addAddress();
        updateAddress();
    }

        public void addAddress() {
            app.post("/api/address", (req, res) -> {
                Listing currentListing = req.session("current-Listing");
                res.json(logicHandler.getAddressLogic().createNewAddress((req.body(
                        AddAddressDTO.class)), currentListing));
            });
        }

        public void updateAddress() {
            app.put("/api/address/", (req, res) -> {
                Listing currentListing = req.session("current-Listing");
                logicHandler.getAddressLogic().updateAddress(req.body(UpdateAddressDTO.class), currentListing);
            });
        }
    }

