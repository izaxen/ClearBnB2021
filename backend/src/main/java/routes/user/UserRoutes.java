package routes.user;

import express.Express;
import models.User;
import repositories.Repositories;

import java.util.Optional;

public class UserRoutes {

    public UserRoutes(Express app, Repositories repository) {

        app.get("/user", ((req, res) -> {

            Optional<User> optUser = repository.getUser().findById(1);
            User user = optUser.get();

            Optional<User> optUser2 = repository.getUser().findById(2);
            User user2 = optUser2.get();

            res.json(user);
        }));
    }
}
