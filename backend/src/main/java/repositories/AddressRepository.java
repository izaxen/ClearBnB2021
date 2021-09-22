package repositories;

import jakarta.persistence.EntityManager;
import entityDO.Address;

import java.util.List;
import java.util.Optional;

public class AddressRepository {
    private EntityManager entityManager;

    public AddressRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<Address> findById(Integer id){
        Address address = entityManager.find(Address.class, id);
        return address != null ? Optional.of(address) : Optional.empty();
    }

    public List<Address> findAllAddresses(){
        return entityManager.createQuery("from Address").getResultList();
    }

    public Address addAddress(Address address){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(address);
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return address;
    }
}
