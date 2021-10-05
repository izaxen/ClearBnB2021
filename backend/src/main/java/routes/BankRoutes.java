package routes;

import application.LogicHandler;
import dtos.AddBankDTO;
import entityDO.BankAccount;
import entityDO.User;
import express.Express;

public class BankRoutes {

    public BankRoutes(Express app, LogicHandler logicHandler) {

        app.post("api/bank",(req, res) -> {
            User currentUser = req.session("current-user");
            BankAccount userBank = (logicHandler.getBankLogic().createNewBank(req.body(AddBankDTO.class), currentUser));
            User userWithBankAcc = userBank.getUser();
            req.session("current-user", userWithBankAcc);
        });

    }
}
