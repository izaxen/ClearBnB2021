package models;

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

    @Column (name = "bathtub")
    private boolean isBathTub;

    @Column (name = "parkinglot")
    private boolean isParkingLot;

    @Column (name = "stove")
    private boolean isStove;

    @Column (name = "double_bed")
    private boolean isDoubleBed;

    @Column (name = "bubble_pool")
    private boolean isBubblePool;

    @Column (name = "cycle")
    private boolean isCycle;

    @Column (name = "sauna")
    private boolean isSauna;

    public Amenities(Listing listing, boolean isBathTub, boolean isParkingLot, boolean isStove, boolean isDoubleBed, boolean isBubblePool, boolean isCycle, boolean isSauna) {
        this.listing = listing;
        this.isBathTub = isBathTub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isCycle = isCycle;
        this.isSauna = isSauna;
    }

    public Amenities() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public boolean isBathTub() {
        return isBathTub;
    }

    public void setIsBathTub(boolean bathTub) {
        isBathTub = bathTub;
    }

    public boolean isParkingLot() {
        return isParkingLot;
    }

    public void setIsParkingLot(boolean parkingLot) {
        isParkingLot = parkingLot;
    }

    public boolean isStove() {
        return isStove;
    }

    public void setIsStove(boolean stove) {
        isStove = stove;
    }

    public boolean isDoubleBed() {
        return isDoubleBed;
    }

    public void setIsDoubleBed(boolean doubleBed) {
        isDoubleBed = doubleBed;
    }

    public boolean isBubblePool() {
        return isBubblePool;
    }

    public void setIsBubblePool(boolean bubblePool) {
        isBubblePool = bubblePool;
    }

    public boolean isCycle() {
        return isCycle;
    }

    public void setIsCycle(boolean cycle) {
        isCycle = cycle;
    }

    public boolean isSauna() {
        return isSauna;
    }

    public void setIsSauna(boolean sauna) {
        isSauna = sauna;
    }
}
