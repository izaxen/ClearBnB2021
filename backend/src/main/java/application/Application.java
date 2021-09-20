package application;

import express.Express;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositories.UserRepostitory;
import routes.Authorization;


public class Application {

    public Application() {
        Express app = new Express();


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ClearbNb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        UserRepostitory userRepostitory = new UserRepostitory(entityManager);
        //ListingRepository listingRepository = new ListingRepository(entityManager);

        new Authorization(app,userRepostitory);


        app.listen(4000); // Start server on port 4000
        }
    }
