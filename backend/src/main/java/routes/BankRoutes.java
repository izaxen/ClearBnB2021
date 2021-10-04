package routes;

import application.AmenityLogic;
import application.BankLogic;
import application.Repositories;
import dtos.AddAmenityDTO;
import dtos.AddBankDTO;
import dtos.UpdateAmenityDTO;
import entityDO.Amenities;
import entityDO.BankAccount;
import entityDO.Listing;
import entityDO.User;
import express.Express;
import mapper.AmenityService;
import mapper.BankService;

public class BankRoutes {
    private BankLogic bankLogic;
    private BankService bs;


    public BankRoutes(Express app, Repositories repos) {
        this.bankLogic = new BankLogic(repos);
        this.bs = new BankService();

        app.post("api/bank",(req, res) -> {
            User currentUser = req.session("current-user");
            BankAccount userBank = (bankLogic.createNewBank(req.body(AddBankDTO.class), currentUser));
            User userWithBankAcc = userBank.getUser();
            req.session("current-user", userWithBankAcc);
        });

    }
}
