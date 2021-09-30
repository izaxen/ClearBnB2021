package entityDO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
@Table(name = "amenities")
public class Amenities {

    @Id
    @Column(name = "listing_ID")
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
    @JoinColumn(name = "listing_ID")
    private Listing listing;

    public Amenities(){
    }

    public Amenities(Integer id, Boolean isBathTub, Boolean isParkingLot, Boolean isStove, Boolean isDoubleBed,
                     Boolean isBubblePool, Boolean isBicycle, Boolean isSauna, Listing listing) {
        this.id = id;
        this.isBathTub = isBathTub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isBicycle = isBicycle;
        this.isSauna = isSauna;
        this.listing = listing;
    }

    public Amenities(Boolean isBathTub, Boolean isParkingLot, Boolean isStove, Boolean isDoubleBed,
                     Boolean isBubblePool, Boolean isBicycle, Boolean isSauna, Listing listing) {
        this.isBathTub = isBathTub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isBicycle = isBicycle;
        this.isSauna = isSauna;
        this.listing = listing;
    }

    public Amenities(Integer id, Boolean isBathTub, Boolean isParkingLot, Boolean isStove,
                     Boolean isDoubleBed, Boolean isBubblePool, Boolean isBicycle, Boolean isSauna) {
        this.id = id;
        this.isBathTub = isBathTub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isBicycle = isBicycle;
        this.isSauna = isSauna;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public Boolean getBathTub() {
        return isBathTub;
    }

    public void setBathTub(Boolean bathTub) {
        isBathTub = bathTub;
    }

    public Boolean getParkingLot() {
        return isParkingLot;
    }

    public void setParkingLot(Boolean parkingLot) {
        isParkingLot = parkingLot;
    }

    public Boolean getStove() {
        return isStove;
    }

    public void setStove(Boolean stove) {
        isStove = stove;
    }

    public Boolean getDoubleBed() {
        return isDoubleBed;
    }

    public void setDoubleBed(Boolean doubleBed) {
        isDoubleBed = doubleBed;
    }

    public Boolean getBubblePool() {
        return isBubblePool;
    }

    public void setBubblePool(Boolean bubblePool) {
        isBubblePool = bubblePool;
    }

    public Boolean getBicycle() {
        return isBicycle;
    }

    public void setBicycle(Boolean bicycle) {
        isBicycle = bicycle;
    }

    public Boolean getSauna() {
        return isSauna;
    }

    public void setSauna(Boolean sauna) {
        isSauna = sauna;
    }

    @Override
    public String toString() {
        return "Amenities{" +
                "id=" + id +
                ", isBathTub=" + isBathTub +
                ", isParkingLot=" + isParkingLot +
                ", isStove=" + isStove +
                ", isDoubleBed=" + isDoubleBed +
                ", isBubblePool=" + isBubblePool +
                ", isBicycle=" + isBicycle +
                ", isSauna=" + isSauna +
                ", listing=" + listing +
                '}';
    }
}

