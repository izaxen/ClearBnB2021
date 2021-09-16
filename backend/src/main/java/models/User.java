package models;

import jakarta.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int ID;
    @Column (name = "first_name")
    private String name;
    @Column (name = "sur_name")
    private String surName;
    private String email;
    private int funds =10000;
    private String pw;

    public User(){

    }

    public User(String firstName, String lastName, String email, String pw) {

        this.name = firstName;
        this.surName = lastName;
        this.email = email;
        this.pw = pw;

    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", lastName='" + surName + '\'' +
                ", email='" + email + '\'' +
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
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
