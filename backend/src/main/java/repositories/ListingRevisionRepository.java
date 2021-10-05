package repositories;

import jakarta.persistence.EntityManager;
import entityDO.ListingRevision;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Optional;

public class ListingRevisionRepository {
    private EntityManagerFactory emf;


    public ListingRevisionRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Optional<ListingRevision> findById (Integer id){
        EntityManager em= emf.createEntityManager();
        ListingRevision listingRevision = em.find(ListingRevision.class, id);
        em.close();
        return listingRevision != null ? Optional.of(listingRevision) : Optional.empty();
    }


    public ListingRevision findRevIDByID(int id){
        EntityManager em= emf.createEntityManager();
        List<ListingRevision> lrp = em.createQuery("FROM ListingRevision lr WHERE lr.listing.id = :id"
                        ,ListingRevision.class)
                .setParameter("id", id)
                .getResultList();

        em.close();
        ListingRevision e = lrp.get(lrp.size()-1);
        return e;
    }

    // We will get a list of all listing revisions belonging to ONE listing, if we have listingID
    public List<ListingRevision> findAllListingRevisionsByListingID(int listingID){
        EntityManager em= emf.createEntityManager();
        List<ListingRevision> list = em.createQuery("FROM ListingRevision lr WHERE lr.listing.id = :listingID", ListingRevision.class)
                .setParameter("listingID", listingID)
                .getResultList();
        em.close();
        return list;
    }

    public Optional<ListingRevision> addListingRevision(ListingRevision listingRevision){
        EntityManager em= emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(listingRevision);
            em.getTransaction().commit();
            em.close();
            return Optional.of(listingRevision);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return Optional.empty();
    }

    public Optional<ListingRevision> updateListingRevision(ListingRevision listingRevision){
        EntityManager em= emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.   merge(listingRevision);
            em.getTransaction().commit();
            em.close();
            return Optional.of(listingRevision);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return Optional.empty();
    }
}

