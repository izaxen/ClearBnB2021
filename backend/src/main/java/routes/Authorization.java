package routes;

import application.UserAccess;
import express.Express;

import models.User;
import repositories.UserRepository;
import utils.HashPassword;

import static nosqlite.Database.collection;


import java.util.Map;

public class Authorization {
    private Express app;
    private UserAccess userAccess;


        public Authorization(Express app, UserRepository repository){
        this.app=app;
        userAccess = new UserAccess(repository);
        initAuthorization();
    }

    private void initAuthorization() {

        app.post("/api/registerUser", (req, res) -> {   //Create user
            User user = userAccess.createNewUser(req.body(User.class));
            req.session("current-user", user);
            res.json(user);
        });



        app.post("/api/login", (req, res) -> {
            User user = req.body(User.class);
            User userInCollection = collection("User").findOne("email==" +user.getEmail());

            if(userInCollection == null){
                res.json(Map.of("error", "Control login details")); //Console Variable
                return;
            }

            if(HashPassword.match(user.getPw(), userInCollection.getPw())){
                req.session("current-user", userInCollection);

                res.json(userInCollection);
            }
            else{
                res.json(Map.of("error", "Control login details"));
            }

        });

        app.get("/api/whoami", (req, res)-> {   //Control logged in user

            res.json(req.session("current-user"));
        });

        app.get("/api/logout",(req,res)->{
            req.session("current-user", null);

            res.json(Map.of("ok", "Logged out"));
        });

            }
}

