package models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
@NamedQueries({
        @NamedQuery(name = "User.findByName",
        query = "SELECT u FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName"),

//        @NamedQuery(name = "User.findAllUsers",
//        query = "SELECT u FROM User u")
})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "sur_name")
    private String lastName;
    private String email;
    private int funds;

    private String password;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Listing> listings = new ArrayList<>();

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Booking> bookings = new ArrayList<>();

    public User(String firstName, String lastName, String email, int funds, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.funds = funds;
        this.password = password;
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

    public List<Listing> getListings() {
        return listings;
    }

    public void addListings(Listing listing) {
        listings.add(listing);
        listing.setUser(this);
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
                '}';
    }
}
