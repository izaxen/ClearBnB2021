package repositories;

import jakarta.persistence.EntityManager;
import models.Listing;
import models.ListingRevision;

import java.util.List;
import java.util.Optional;

public class ListingRevisionRepository {

    private EntityManager entityManager;

    public ListingRevisionRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<ListingRevision> findById (Integer id){
        ListingRevision listingRevision = entityManager.find(ListingRevision.class, id);
        return listingRevision != null ? Optional.of(listingRevision) : Optional.empty();
    }

    public List<ListingRevision> findAllListingRevisions(){
        return entityManager.createQuery("from ListingRevision").getResultList();
    }

    public Optional<ListingRevision> addListingRevision(ListingRevision listingRevision){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(listingRevision);
            entityManager.getTransaction().commit();
            return Optional.of(listingRevision);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
