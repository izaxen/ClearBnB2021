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


        //User user = new User("Mackan","Udd","bla@bla.com",1000,"lösen");
        //Optional<User> savedUser = userRepostitory.save(user);

        List<User> users = userRepostitory.findAll();
        System.out.println("users:");
        users.forEach(System.out::println);
        //userRepostitory.findById(1);

        //doExampleQuery();
        //doExampleQuery1();



        app.get("/api/", (req, res) -> {
            res.json("Hello World");
        });

        app.listen(4000); // Start server on port 4000
    }
}
