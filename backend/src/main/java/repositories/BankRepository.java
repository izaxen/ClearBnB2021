package repositories;

import application.Repositories;
import entityDO.Address;
import entityDO.BankAccount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class BankRepository {
    private EntityManagerFactory emf;


    public BankRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public BankAccount addBank(BankAccount bank){
        EntityManager em= emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(bank);
            em.getTransaction().commit();
            em.close();

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
        return bank;
    }
}
