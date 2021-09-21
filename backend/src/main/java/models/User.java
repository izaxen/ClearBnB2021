package models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name="user")
/*@NamedQueries({
        @NamedQuery(name="User.findByName",
        query = "SELECT u FROM User u WHERE u.name = :name"),
        @NamedQuery(name = "User.findAll",
            query ="SELECT u FROM User u")
})*/
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int ID;
    @Column (name = "first_name")
    private String firstName;
    @Column (name = "sur_name")
    private String surName;
    private String email;
    private int funds = 10000;
    private String pw;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date date_created;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Listing> listings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    private List<Rating> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Rating> rating = new ArrayList<>();

    public User(String firstName, String lastName, String email, int funds, String pw) {
        this.firstName = firstName;
        this.surName = lastName;
        this.email = email;
        this.funds = funds;
        this.pw = pw;
    }

    public User() {
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

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + surName + '\'' +
                ", email='" + email + '\'' +
                ", funds=" + funds +
                ", password='" + pw + '\'' +
                '}';
    }
}
