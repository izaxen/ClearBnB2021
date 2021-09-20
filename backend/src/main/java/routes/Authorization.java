package routes;

import express.Express;

import models.User;
import repositories.UserRepostitory;
import utils.HashPassword;

import static nosqlite.Database.collection;


import java.util.Map;
import java.util.Optional;

public class Authorization {
    private Express app;
    private UserRepostitory userRepostitory;

        public Authorization(Express app, UserRepostitory userRepostitory){
        this.app=app;
        this.userRepostitory = userRepostitory;
        initAuthorization();
    }

    private void initAuthorization() {

        app.post("/api/registerUser", (req, res) -> {   //Create user
            User user = req.body(User.class);

            String hashedPassword = HashPassword.hash(user.getPw());
            user.setPw(hashedPassword);
            Optional<User> savedUser = userRepostitory.save(user);

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

