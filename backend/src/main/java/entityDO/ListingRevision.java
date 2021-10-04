package entityDO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "listing_revision")
public class ListingRevision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Integer price;

    private String description;

    @Column(name="available_start_date")
    private String availableStartDate;

    @Column(name="available_end_date")
    private String availableEndDate;

    @ManyToOne
    @JoinColumn(name="listing_ID")
    private Listing listing;

    @JsonManagedReference
    @OneToOne(mappedBy = "listingRevi", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AddressRevision addressRevision;

    // HERE........
    @JsonManagedReference
    @OneToOne(mappedBy = "listingRev", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AmenitiesRevision amenitiesRevision;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="owner_ID")
    private User user;

    public ListingRevision() {
    }

    public ListingRevision(Integer price, String description, String availableStartDate,
                           String availableEndDate, Listing listing, User user) {
        this.price = price;
        this.description = description;
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
        this.listing = listing;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public ListingRevision(Listing listing) {
        this.listing = listing;
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

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AddressRevision getAddressRevision() {
        return addressRevision;
    }

    public void setAddressRevision(AddressRevision addressRevision) {
        this.addressRevision = addressRevision;
    }

    public AmenitiesRevision getAmenitiesRevsion() {
        return amenitiesRevision;
    }

    public void setAmenitiesRevsion(AmenitiesRevision amenitiesRevision) {
        this.amenitiesRevision = amenitiesRevision;
    }

    @Override
    public String toString() {
        return "ListingRevision{" +
                "id=" + id +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", availableStartDate='" + availableStartDate + '\'' +
                ", availableEndDate='" + availableEndDate + '\'' +
                '}';
    }
}
