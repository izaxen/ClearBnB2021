package repositories;

import entityDO.BankAccount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class BankRepository {
    private EntityManagerFactory emf;

    public BankRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void updateBank(BankAccount bank){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(bank);
            em.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        em.close();
    }
}
