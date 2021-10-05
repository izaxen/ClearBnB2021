package repositories;

import jakarta.persistence.EntityManager;
import entityDO.AddressRevision;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Optional;

public class AddressRevisionRepository {

    private EntityManagerFactory emf;


    public AddressRevisionRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Optional<AddressRevision> findByID(Integer ID){
        EntityManager em= emf.createEntityManager();
        AddressRevision addressRevision = em.find(AddressRevision.class, ID);
        return addressRevision != null ? Optional.of(addressRevision) : Optional.empty();
    }

    public List<AddressRevision> findAllAddressRevisions(){
        EntityManager em= emf.createEntityManager();
        List<AddressRevision> list = em.createQuery("from AddressRevision").getResultList();
        return list;
    }

    // Should return a list of all address revision under a listing revision's id
    // Considering we only have 1 address revision for each listing revision, we can add .get(0)
    // to return the only result (ofc need to change the List type then)
    public List<AddressRevision> findAllByListingRevisionID(Integer listingRevID){
        EntityManager em= emf.createEntityManager();
        List<AddressRevision> list = em.createQuery("SELECT arr FROM AddressRevision arr WHERE arr.listingRevision = :listingRevID", AddressRevision.class)
                .setParameter("listing_rev_ID", listingRevID)
                .getResultList();
        return list;
    }

    public Optional<AddressRevision> addAddressRevision(AddressRevision addressRevision){
        EntityManager em= emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(addressRevision);
            em.getTransaction().commit();
            em.close();
            return Optional.of(addressRevision);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return Optional.empty();
    }

    public Optional<AddressRevision> updateAddressRevision(AddressRevision addressRevision){
        EntityManager em= emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.merge(addressRevision);
            em.getTransaction().commit();
            em.close();
            return Optional.of(addressRevision);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return Optional.empty();
    }
}
