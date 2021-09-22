package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String city;
    private String addressListing;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name="listing_ID")
    private Listing listing;

    public Address(String city, String address) {
        this.city = city;
        this.addressListing = address;
    }

    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
