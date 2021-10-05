package routes;

import application.UserLogic;
import dtos.LoginUserDTO;
import dtos.RegisterUserDTO;
import express.Express;
import entityDO.User;
import repositories.UserRepository;
import mapper.UserMapper;

import java.util.Map;

public class UserRoutes {

    private UserLogic userAccess;
    private UserMapper userMapper;

    public UserRoutes(Express app, UserRepository userRepository) {

        userAccess = new UserLogic(userRepository);
        userMapper = new UserMapper();

        app.post("/api/registerUser", (req, res) -> {   //Create user
            User user = userAccess.createNewUser(userMapper.convertRegisterUser(req.body(RegisterUserDTO.class)));
            req.session("current-user", user);
            res.json(userMapper.noPwUser(user));
        });

        app.post("/api/login", (req, res) -> {
            User user = userAccess.loginUser(
                    userMapper.convertLoginUserToUser(
                    req.body(LoginUserDTO.class)));

            if( user != null) {
                req.session("current-user", user);
                res.json(userMapper.noPwUser(user));
            }
            else{
                res.json(Map.of("Error", "Logindetails failed"));}
        });

        app.get("/api/whoAmI", (req, res)-> {   //Control logged in user

            res.json(userMapper.noPwUser(req.session("current-user")));
        });

        app.get("/rest/logOff",(req,res)->{

            req.session("current-user", null);
            res.json(Map.of("Ok", "Logged out"));
        });

    }
}
