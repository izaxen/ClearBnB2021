package routes;

import application.AddressLogic;
import application.Repositories;
import dtos.AddAddressDTO;
import dtos.UpdateAddressDTO;
import entityDO.Listing;
import express.Express;
import entityDO.Address;
import mapper.AddressService;

public class AddressRoutes {

    private AddressLogic addressLogic;
    private AddressService as;


    public AddressRoutes(Express app, Repositories repo) {

        addressLogic= new AddressLogic(repo);
        as = new AddressService();

        app.post("/api/addAddress", (req, res) -> {   //Create listing

            Listing currentListing = req.session("current-Listing");
            Address address = addressLogic.createNewAddress(
                    as.convertAddAddressToAddress(req.body(
                            AddAddressDTO.class),currentListing)
            );
            res.json(address);
        });

        app.post("/api/updateAddress", (req, res) -> {   //Create listing

            Listing currentListing = req.session("current-Listing");
            Address address = addressLogic.updateAddress(
                    as.convertUpdateAddressToAddress(req.body(
                            UpdateAddressDTO.class),currentListing)
            );
            res.json(address);
        });


    }
}
