package entityDO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name = "address")

public class Address {
    @Id
    @Column(name = "listing_id")
    private int id;
    private String city;
    private String addressListing;

    @JsonBackReference
    @OneToOne
    @MapsId
    @JoinColumn(name="listing_id")
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

    public Address(int id, String city, String addressListing, Listing listing) {
        this.id = id;
        this.city = city;
        this.addressListing = addressListing;
        this.listing = listing;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", addressListing='" + addressListing + '\'' +
                '}';
    }
}
