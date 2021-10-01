package entityDO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    
    private int funds = 10000;

    @Column(name="bank_address")
    private String bankAddress;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name="owner_id")
    private User user;

    public BankAccount() {
    }

    public BankAccount(String bankAddress, User user) {
        this.bankAddress = bankAddress;
        this.user = user;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "Id=" + Id +
                ", funds=" + funds +
                ", bankAddress='" + bankAddress + '\'' +
                '}';
    }
}
