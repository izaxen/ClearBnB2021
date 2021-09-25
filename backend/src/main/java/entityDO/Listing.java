package entityDO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import org.hibernate.annotations.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


    // available_start_date is our column in DB
    //:availableStartDate is our user input sent from frontend


@Entity
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int price;

    private String description;

    @Filter(name = "dateFilter",
            condition = ":availableStartDate <= availableStartDate and :availableEndDate >= availableEndDate")
    @Column(name="available_start_date")
    private Timestamp availableStartDate;

    @Filter(name = "dateFilter",
            condition = ":availableStartDate <= availableStartDate and :availableEndDate >= availableEndDate")
    @Column(name="available_end_date")
    private Timestamp availableEndDate;

    @JsonManagedReference
    @OneToOne(mappedBy = "listing", cascade=CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy="listing",cascade = CascadeType.ALL)
    private List<ListingRevision> listingRevisions = new ArrayList<>();

    @JsonManagedReference
    @OneToOne(mappedBy = "listing", cascade=CascadeType.ALL)
    private Amenities amenities;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="owner_ID")
    private User user;

    public Listing() {
    }

    public Listing(Integer price, String description, Timestamp availableStartDate, Timestamp availableEndDate, User owner) {
        this.price = price;
        this.description = description;
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
        this.user = owner;
    }

    public Listing(Timestamp availableStartDate, Timestamp availableEndDate) {
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getAvailableStartDate() {
        return availableStartDate;
    }

    public void setAvailableStartDate(Timestamp availableStartDate) {
        this.availableStartDate = availableStartDate;
    }

    public Timestamp getAvailableEndDate() {
        return availableEndDate;
    }

    public void setAvailableEndDate(Timestamp availableEndDate) {
        this.availableEndDate = availableEndDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Amenities getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenities amenities) {
        this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", availableStartDate='" + availableStartDate + '\'' +
                ", availableEndDate='" + availableEndDate + '\'' +
                ", user=" + user +
                '}';
}
    }
