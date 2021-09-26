package repositories;

import jakarta.persistence.EntityManager;

public class AmenitiesRevisionRepository {
    private EntityManager entityManager;
    public AmenitiesRevisionRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

}
