package repositories;

import entityDO.Listing;
import jakarta.persistence.EntityManager;
import entityDO.Address;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
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

    public List<Address> findAllAddresses(){
        EntityManager em= emf.createEntityManager();
        List<Address> list = em.createQuery("from Address").getResultList();

        return list;
    }

    public Address addAddress(Address address){
        EntityManager em= emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            em.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return address;
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
