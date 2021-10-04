package repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import entityDO.Listing;
import jakarta.persistence.EntityManager;
import entityDO.Image;
import org.bson.Document;

import java.util.List;
import java.util.Optional;

public class   ImageRepository {

    private EntityManager entityManager;

    public ImageRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<Image> findAllImages(int listID){
        return entityManager.createQuery("Select im FROM Image im ", Image.class)
                //.setParameter("listID", listID)
                .getResultList();
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
