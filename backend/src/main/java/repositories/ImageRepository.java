package repositories;

import jakarta.persistence.EntityManager;
import entityDO.Image;

import java.util.Optional;

public class ImageRepository {

    private EntityManager entityManager;

    public ImageRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<Image> addImage(Image image){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(image);
            entityManager.getTransaction().commit();
            return Optional.of(image);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

}
