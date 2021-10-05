package entityDO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
@Entity
@Table(name = "amenities_revision")
public class AmenitiesRevision {

    @Id
    @Column(name = "listing_rev_ID")
    private Integer id;

    @Column(name = "bathtub")
    private Boolean isBathTub;
    @Column(name = "parkinglot")
    private Boolean isParkingLot;
    @Column(name = "stove")
    private Boolean isStove;
    @Column(name = "double_bed")
    private Boolean isDoubleBed;
    @Column(name = "bubble_pool")
    private Boolean isBubblePool;
    @Column(name = "bicycle")
    private Boolean isBicycle;
    @Column(name = "sauna")
    private Boolean isSauna;

    @JsonBackReference
    @OneToOne
    @MapsId
    @JoinColumn(name="listing_rev_ID")
    private ListingRevision listingRev;

    public AmenitiesRevision(){
    }

    public AmenitiesRevision(Boolean isBathTub, Boolean isParkingLot, Boolean isStove, Boolean isDoubleBed,
                             Boolean isBubblePool, Boolean isBicycle, Boolean isSauna, ListingRevision listing) {
        this.isBathTub = isBathTub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isBicycle = isBicycle;
        this.isSauna = isSauna;
        this.listingRev = listing;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Boolean getBathTub() {
        return isBathTub;
    }


    public Boolean getParkingLot() {
        return isParkingLot;
    }


    public Boolean getStove() {
        return isStove;
    }


    public Boolean getDoubleBed() {
        return isDoubleBed;
    }


    public Boolean getBubblePool() {
        return isBubblePool;
    }


    public Boolean getBicycle() {
        return isBicycle;
    }

    public Boolean getSauna() {
        return isSauna;
    }


    @Override
    public String toString() {
        return "AmenitiesRevision{" +
                "isBathTub=" + isBathTub +
                ", isParkingLot=" + isParkingLot +
                ", isStove=" + isStove +
                ", isDoubleBed=" + isDoubleBed +
                ", isBubblePool=" + isBubblePool +
                ", isBicycle=" + isBicycle +
                ", isSauna=" + isSauna +
                '}';
    }
}
