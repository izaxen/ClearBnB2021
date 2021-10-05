package repositories;

import jakarta.persistence.EntityManager;
import entityDO.Address;
import jakarta.persistence.EntityManagerFactory;
import java.util.Optional;

public class AddressRepository {
    private EntityManagerFactory emf;


    public AddressRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public Optional<Address> findById(Integer id){
        EntityManager em= emf.createEntityManager();
        Address address = em.find(Address.class, id);
        return address != null ? Optional.of(address) : Optional.empty();
    }

    public Address updateAddress(Address address){
        EntityManager em= emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.merge(address);
            em.getTransaction().commit();
            em.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return address;
    }
}
