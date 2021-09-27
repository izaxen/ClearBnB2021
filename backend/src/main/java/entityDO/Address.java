package entityDO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
public class Address {
    @Id
    @Column(name = "listing_ID")
    private Integer ID;
    private String city;
    private String addressListing;

    @JsonBackReference
    @OneToOne
    @MapsId
    @JoinColumn(name="listing_ID")
    private Listing listing;

    public Address(String city, String address) {
        this.city = city;
        this.addressListing = address;
    }

    public Address(String city, String addressListing, Listing listing) {
        this.city = city;
        this.addressListing = addressListing;
        this.listing = listing;
    }

    public Address() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public String getAddressListing() {
        return addressListing;
    }

    public void setAddressListing(String address) {
        this.addressListing = address;
    }
}
