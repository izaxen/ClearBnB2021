package routes;

import application.AddressLogic;
import express.Express;
import entityDO.Address;
import repositories.AddressRepository;

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
