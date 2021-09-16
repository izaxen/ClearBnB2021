package models;

import jakarta.persistence.*;


import java.util.Date;
@Entity
@Table (name="user")
@NamedQueries({
        @NamedQuery(name="User.findByName",
        query = "SELECT u FROM User u WHERE u.name = :name"),
        @NamedQuery(name = "User.findAll",
            query ="SELECT u FROM User u")
})
public class User {
    @Id
    private int ID;
    @Column (name = "first_name")
    private String name;
    @Column (name = "sur_name")
    private String lastName;
    private String email;
    private int funds;
    private String pw;


    public User(){

    }

    public User(String firstName, String lastName, String email, int funds, String pw) {
        //this.ID = ID;
        this.name = firstName;
        this.lastName = lastName;
        this.email = email;
        this.funds = funds;
        this.pw = pw;

    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", funds=" + funds +
                ", pw='" + pw + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }


}
