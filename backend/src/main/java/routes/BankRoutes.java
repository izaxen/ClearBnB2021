package routes;

import application.BankLogic;
import application.Repositories;
import dtos.AddBankDTO;
import entityDO.BankAccount;
import entityDO.User;
import express.Express;

public class BankRoutes {
    private BankLogic bankLogic;

    public BankRoutes(Express app, Repositories repos) {
        this.bankLogic = new BankLogic(repos);

        app.post("api/bank",(req, res) -> {
            User currentUser = req.session("current-user");
            BankAccount userBank = (bankLogic.createNewBank(req.body(AddBankDTO.class), currentUser));
            User userWithBankAcc = userBank.getUser();
            req.session("current-user", userWithBankAcc);
        });

    }
}
