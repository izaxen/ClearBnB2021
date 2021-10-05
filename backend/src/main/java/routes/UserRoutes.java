package routes;

import application.LogicHandler;
import application.UserLogic;
import dtos.LoginUserDTO;
import dtos.RegisterUserDTO;
import entityDO.User;
import express.Express;
import mapper.UserMapper;

import java.util.Map;

public class UserRoutes {

    UserLogic userAccess;
    UserMapper userMapper;
    Express app;

    public UserRoutes(Express app, LogicHandler logicHandler) {

        this.userAccess = logicHandler.getUserLogic();
        this.userMapper = new UserMapper();
        this.app = app;
        createNewUser();
        loginUser();
        whoAmI();
        logOutUser();
    }

    private void createNewUser() {
        app.post("/api/registerUser", (req, res) -> {   //Create user
            User user = userAccess.createNewUser(req.body(RegisterUserDTO.class));
            req.session("current-user", user);
            res.json(userMapper.noPwUser(user));
        });
    }

    private void loginUser() {
        app.post("/api/login", (req, res) -> {
            User user = userAccess.loginUser(req.body(LoginUserDTO.class));

            if (user != null) {
                req.session("current-user", user);
                res.json(userMapper.noPwUser(user));
            } else {
                res.json(Map.of("Error", "Logindetails failed"));
            }
        });
    }

    private void whoAmI() {
        app.get("/api/whoAmI", (req, res) -> {   //Control logged in user
            res.json(userMapper.noPwUser(req.session("current-user")));
        });
    }

    private void logOutUser() {
        app.get("/rest/logOff", (req, res) -> {

            req.session("current-user", null);
            res.json(Map.of("Ok", "Logged out"));
        });
    }
}
