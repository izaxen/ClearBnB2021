package utils;

import express.Express;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.User;
import models.UserRepostitory;

import java.util.List;

public class Routes {


    public Routes() {

        Express app = new Express();


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ClearbNb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserRepostitory userRepostitory = new UserRepostitory(entityManager);

        new Authorization(app,userRepostitory);


        app.get("/api/", (req, res) -> {
            res.json("Hello World");
        });

        app.listen(4000); // Start server on port 4000
    }
}
