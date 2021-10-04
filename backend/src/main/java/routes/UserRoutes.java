package routes;

import application.UserAccess;
import dtos.LoginUserDTO;
import dtos.RegisterUserDTO;
import express.Express;
import entityDO.User;
import repositories.UserRepository;
import mapper.UserService;

import java.util.Map;

public class UserRoutes {

    private UserAccess userAccess;
    private UserService userService;

    public UserRoutes(Express app, UserRepository userRepository) {

        userAccess = new UserAccess(userRepository);
        userService = new UserService();

        app.post("/api/registerUser", (req, res) -> {   //Create user
            User user = userAccess.createNewUser(userService.convertRegisterUser(req.body(RegisterUserDTO.class)));
            req.session("current-user", user);
            res.json(userService.noPwUser(user));
        });

        app.post("/api/login", (req, res) -> {
            User user = userAccess.loginUser(
                    userService.convertLoginUserToUser(
                    req.body(LoginUserDTO.class)));

            if( user != null) {
                req.session("current-user", user);
                res.json(userService.noPwUser(user));
            }
            else{
                res.json(Map.of("Error", "Logindetails failed"));}
        });

        app.get("/api/whoAmI", (req, res)-> {   //Control logged in user

            res.json(userService.noPwUser(req.session("current-user")));
        });

        app.get("/api/logOff",(req,res)->{
            req.session("current-user", null);
            res.json(Map.of("Ok", "Logged out"));
        });

    }
}
