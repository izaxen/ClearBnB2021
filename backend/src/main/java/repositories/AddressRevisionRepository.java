package repositories;

import jakarta.persistence.EntityManager;
import models.Address;
import models.AddressRevision;
import models.User;

import java.util.List;
import java.util.Optional;

public class AddressRevisionRepository {

    private EntityManager entityManager;

    public AddressRevisionRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<AddressRevision> findByID(Integer ID){
        AddressRevision addressRevision = entityManager.find(AddressRevision.class, ID);
        return addressRevision != null ? Optional.of(addressRevision) : Optional.empty();
    }

    public List<AddressRevision> findAllAddressRevisions(){
        return entityManager.createQuery("from AddressRevision").getResultList();
    }

    // Should return a list of all address revision under a listing revision's id
    // Considering we only have 1 address revision for each listing revision, we can add .get(0)
    // to return the only result (ofc need to change the List type then)
    public List<AddressRevision> findAllByListingRevisionID(Integer listingRevID){
        return entityManager.createQuery("SELECT arr FROM AddressRevision arr WHERE arr.listingRevision = :listingRevID", AddressRevision.class)
                .setParameter("listing_rev_ID", listingRevID)
                .getResultList();
    }

    public Optional<AddressRevision> addAddressRevision(AddressRevision addressRevision){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(addressRevision);
            entityManager.getTransaction().commit();
            return Optional.of(addressRevision);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
