package entityDO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Amenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "listing_ID")
    private Listing listing;

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

    public Amenities(){
    }

    public Amenities(Listing listing, Boolean isBathTub, Boolean isParkingLot, Boolean isStove, Boolean isDoubleBed, Boolean isBubblePool, Boolean isBicycle, Boolean isSauna) {
        this.listing = listing;
        this.isBathTub = isBathTub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isBicycle = isBicycle;
        this.isSauna = isSauna;
    }

    public Integer getID() {
        return ID;
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

    public void setBicycle(Boolean cycle) {
        isBicycle = cycle;
    }

    public Boolean getSauna() {
        return isSauna;
    }

    public void setSauna(Boolean sauna) {
        isSauna = sauna;
    }
}

