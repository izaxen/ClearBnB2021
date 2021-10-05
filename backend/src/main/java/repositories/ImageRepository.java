package repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import entityDO.Listing;
import jakarta.persistence.EntityManager;
import entityDO.Image;
import jakarta.persistence.EntityManagerFactory;
import org.bson.Document;

import java.util.List;
import java.util.Optional;

public class   ImageRepository {

    private EntityManagerFactory emf;


    public ImageRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public List<Image> findAllImages(int listID){
        EntityManager em= emf.createEntityManager();
        List<Image> list = em.createQuery("Select im FROM Image im ", Image.class)
                //.setParameter("listID", listID)
                .getResultList();
          return list;
    }

    public Optional<Image> addImage(Image image){
        EntityManager em= emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(image);
            em.getTransaction().commit();
            em.close();
            return Optional.of(image);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return Optional.empty();
    }
}
