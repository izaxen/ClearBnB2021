package entityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//This was created to fix a bug with "Caused by: java.sql.SQLException: Operation not allowed after ResultSet closed", not sure if this was the solution, but it works now. //Mac
public class EntityManagerCopy {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ClearBnB2021");
    jakarta.persistence.EntityManager entityManager;

    public EntityManagerCopy() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
