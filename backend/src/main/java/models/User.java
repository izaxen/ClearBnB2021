package models;

import jakarta.persistence.Entity;
import nosqlite.annotations.Id;

import java.util.Date;
@Entity
public class User {
    @Id
    private int ID;

    private String firstName;
    private String lastName;
    private String email;
    private int funds;
    private String password;
    private Date date_created;

    public User(){

    }

    public User(int ID, String firstName, String lastName, String email, int funds, String password, Date date_created) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.funds = funds;
        this.password = password;
        this.date_created = date_created;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", funds=" + funds +
                ", password='" + password + '\'' +
                ", date_created=" + date_created +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFunds() {
        return funds;
    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }
}
