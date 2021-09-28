package entityDO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="address_revision")
public class AddressRevision {
    @Id
    @Column(name = "listing_rev_ID")
    private Integer id;

    private String city;
    private String address;

    @JsonBackReference
    @OneToOne
    @MapsId
    @JoinColumn(name="listing_rev_ID")
    private ListingRevision listingRevision;

    public AddressRevision() {
    }

    public AddressRevision(ListingRevision lr, String city, String address) {
        this.city = city;
        this.address = address;
        this.listingRevision = lr;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ListingRevision getListingRevision() {
        return listingRevision;
    }

    public void setListingRevision(ListingRevision listingRevision) {
        this.listingRevision = listingRevision;
    }
}
