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
            System.out.println("Address 1");
            Address address = addressLogic.createNewAddress(req.body(Address.class));
            System.out.println("Address 2");
            res.json(address);
        });

    }
}
