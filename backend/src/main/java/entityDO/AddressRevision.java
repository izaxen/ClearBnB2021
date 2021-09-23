package entityDO;

import jakarta.persistence.*;

@Entity
@Table(name="address_revision")
public class AddressRevision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city;
    private String address;

    @OneToOne
    @JoinColumn(name="listing_rev_ID")
    private ListingRevision listingRevision;

    public AddressRevision() {
    }

    public AddressRevision(String city, String address) {
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
        return listingRevision;
    }

    public void setListingRevision(ListingRevision listingRevision) {
        this.listingRevision = listingRevision;
    }
}
