package routes.address;

import application.address.AddressLogic;
import application.listing.ListingLogic;
import express.Express;
import models.Address;
import models.Listing;
import repositories.AddressRepository;
import repositories.ListingRepository;

public class AddressRoutes {

    private AddressLogic addressLogic;

    public AddressRoutes(Express app, AddressRepository addressRepository) {

        addressLogic= new AddressLogic(addressRepository);

        app.post("/api/addAddress", (req, res) -> {   //Create listing
            Address address = addressLogic.createNewAddress(req.body(Address.class));
            res.json(address);
        });

    }
}
