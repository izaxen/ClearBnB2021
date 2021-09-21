package routes.user;

import application.user.UserAccess;
import express.Express;
import models.User;
import repositories.UserRepository;
import java.util.Map;

public class UserRoutes {

    private UserAccess userAccess;

    public UserRoutes(Express app, UserRepository userRepository) {

        userAccess = new UserAccess(userRepository);

        app.post("/api/registerUser", (req, res) -> {   //Create user
            User user = userAccess.createNewUser(req.body(User.class));
            req.session("current-user", user);
            res.json(user);
        });

        app.post("/api/login", (req, res) -> {
            User user = userAccess.loginUser(req.body(User.class));
            if( user != null) {
                req.session("current-user", user);
                res.json(user);
            }
            else{
                res.json(Map.of("Error", "Logindetails failed"));}
        });

        app.get("/api/whoAmI", (req, res)-> {   //Control logged in user
            res.json(req.session("current-user"));
        });

        app.get("/api/logOff",(req,res)->{
            req.session("current-user", null);

            res.json(Map.of("Ok", "Logged out"));
        });

    }
}
