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

        app.post("/api/address", (req, res) -> {   //Create listing
            Listing currentListing = req.session("current-Listing");
            res.json(addressLogic.createNewAddress((req.body(
                    AddAddressDTO.class)), currentListing));
        });

        app.put("/api/address/", (req, res) -> {   //update listing
            Listing currentListing = req.session("current-Listing");
            Listing list = addressLogic.updateAddress(
                    as.convertUpdateAddressToAddress(req.body(
                            UpdateAddressDTO.class), currentListing),currentListing
            );
            System.out.println("in router: " + list);
            //res.json();
        });


    }
}
