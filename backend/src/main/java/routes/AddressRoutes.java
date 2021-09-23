package routes;

import application.AddressLogic;
import dtos.AddAddressDTO;
import dtos.AddListingDTO;
import entityDO.Listing;
import express.Express;
import entityDO.Address;
import repositories.AddressRepository;
import service.AddressService;

public class AddressRoutes {

    private AddressLogic addressLogic;
    private AddressService as;


    public AddressRoutes(Express app, AddressRepository addressRepository) {

        addressLogic= new AddressLogic(addressRepository);
        as = new AddressService();

        app.post("/api/addAddress", (req, res) -> {   //Create listing

            Listing currentListing = req.session("current-Listing");
            Address address = addressLogic.createNewAddress(
                    as.convertAddAddressToAddress(req.body(AddAddressDTO.class),currentListing)
            );
            res.json(address);
        });

    }
}
