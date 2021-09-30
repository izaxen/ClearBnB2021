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

    @Column(name="available_start_date")
    private String availableStartDate;

    @Column(name="available_end_date")
    private String availableEndDate;


    @JsonManagedReference
    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy="listing",cascade = CascadeType.ALL)
    private List<ListingRevision> listingRevisions = new ArrayList<>();

    @JsonManagedReference
    @OneToOne(mappedBy = "listing", cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;


    @JsonManagedReference
    @OneToOne(mappedBy = "listing", cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Amenities amenities;

    @JsonManagedReference
    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="owner_ID")
    private User user;

    public Listing() {
    }

    public Listing(Integer price, String description, String availableStartDate, String availableEndDate, User owner) {
        this.price = price;
        this.description = description;
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
        this.user = owner;
    }

    public Listing(int id, int price, String description, String availableStartDate, String availableEndDate, User user) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
        this.user = user;
    }

    public Listing(String availableStartDate, String availableEndDate) {
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
    }

    public Listing(int id, int price, String description, String availableStartDate, String availableEndDate, Address address, List<Booking> bookings, List<ListingRevision> listingRevisions, Amenities amenities, List<Image> images, User user) {
        this.id = id;
        this.price = (int) (price * 1.15);
        this.description = description;
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
        this.address = address;
        this.bookings = bookings;
        this.listingRevisions = listingRevisions;
        this.amenities = amenities;
        this.images = images;
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
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

    public String getAvailableStartDate() {
        return availableStartDate;
    }

    public void setAvailableStartDate(String availableStartDate) {
        this.availableStartDate = availableStartDate;
    }

    public String getAvailableEndDate() {
        return availableEndDate;
    }

    public void setAvailableEndDate(String availableEndDate) {
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
