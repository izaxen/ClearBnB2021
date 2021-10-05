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
    private ListingRevision listingRevi;

    public AddressRevision() {
    }

    public AddressRevision(String city, String address, ListingRevision listRev) {
        this.city = city;
        this.address = address;
        this.listingRevi = listRev;
    }

    public AddressRevision(Integer id, String city, String address) {
        this.id = id;
        this.city = city;
        this.address = address;
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
        return listingRevi;
    }

    public void setListingRevision(ListingRevision listingRevision) {
        this.listingRevi = listingRevision;
    }

    public ListingRevision getListingRevi() {
        return listingRevi;
    }

    public void setListingRevi(ListingRevision listingRevi) {
        this.listingRevi = listingRevi;
    }

    @Override
    public String toString() {
        return "AddressRevision{" +
                "city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
