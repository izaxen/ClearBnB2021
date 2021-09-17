package models;

import jakarta.persistence.*;

@Entity
public class ListingRevision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    @OneToOne(mappedBy = "listing")
//    private Address address;

//    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
//    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="owner_ID")
    private User user;

    public ListingRevision() {
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


}
