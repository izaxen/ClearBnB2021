package entityDO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
@NamedQueries({
        @NamedQuery(name = "User.findByName",
        query = "SELECT u FROM User u WHERE u.firstName = :firstName AND u.surName = :surName"),


//        @NamedQuery(name = "User.updateUser2",
//                query = "UPDATE User u SET u.firstName = :firstName u.lastName = :lastName")

        @NamedQuery(name = "User.findAllUsers",
        query = "SELECT u FROM User u")
})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
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
    private Date dateCreated;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Listing> listings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    private List<Rating> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Rating> rating = new ArrayList<>();

    public User() {
    }

    public User(String firstName, String lastName, String email, int funds, String pw) {
        this.firstName = firstName;
        this.surName = lastName;
        this.email = email;
        this.funds = funds;
        this.pw = pw;
    }

    public User(String firstName, String surName, String email, String pw) {
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.pw = pw;
    }

    public User(String firstName, String surName, String email) {
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
    }

    public User(String email, String pw) {
        this.email = email;
        this.pw = pw;
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

    public List<Listing> getListings() {
        return listings;
    }

    public void addListings(Listing listing) {
        listings.add(listing);
        listing.setUser(this);
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

