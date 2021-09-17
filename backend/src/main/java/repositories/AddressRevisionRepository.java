package repositories;

import jakarta.persistence.EntityManager;
import models.Address;
import models.AddressRevision;

import java.util.List;
import java.util.Optional;

public class AddressRevisionRepository {

    private EntityManager entityManager;

    public AddressRevisionRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<AddressRevision> findById(Integer id){
        AddressRevision addressRevision = entityManager.find(AddressRevision.class, id);
        return addressRevision != null ? Optional.of(addressRevision) : Optional.empty();
    }

    public List<AddressRevision> findAllAddressRevisions(){
        return entityManager.createQuery("from AddressRevision").getResultList();
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
