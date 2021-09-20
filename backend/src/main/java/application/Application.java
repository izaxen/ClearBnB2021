package application;

import express.Express;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositories.UserRepository;
import routes.Authorization;


public class Application {

    public Application() {
        Express app = new Express();


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ClearbNb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        UserRepository userRepository = new UserRepository(entityManager);
        //ListingRepository listingRepository = new ListingRepository(entityManager);

        new Authorization(app);


        app.listen(4000); // Start server on port 4000
        }
    }
