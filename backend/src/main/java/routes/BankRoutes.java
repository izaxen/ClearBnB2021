package routes;

import application.LogicHandler;
import dtos.AddBankDTO;
import entityDO.BankAccount;
import entityDO.User;
import express.Express;

public class BankRoutes {

    private Express app;
    private LogicHandler logicHandler;

    public BankRoutes(Express app, LogicHandler logicHandler) {
        this.app = app;
        this.logicHandler = logicHandler;
        addBankAccount();
    }

    public void addBankAccount(){
        app.post("api/bank",(req, res) -> {
            User currentUser = req.session("current-user");
            User updatedUser = (logicHandler.getBankLogic().createNewBank(req.body(AddBankDTO.class), currentUser));
            req.session("current-user", updatedUser);
        });
    }
}
