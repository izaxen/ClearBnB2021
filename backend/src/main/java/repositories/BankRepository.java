package repositories;

import application.Repositories;
import entityDO.Address;
import entityDO.BankAccount;
import jakarta.persistence.EntityManager;

public class BankRepository {
    private EntityManager entityManager;

    public BankRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public BankAccount addBank(BankAccount bank){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(bank);
            entityManager.getTransaction().commit();
            entityManager.clear();

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return bank;
    }
}
