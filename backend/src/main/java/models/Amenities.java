package models;

import jakarta.persistence.*;

@Entity
public class Amenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @OneToOne
    @JoinColumn(name = "listing_ID")
    private Listing listing;

    @Column (name = "bathtub")
    private boolean isBathtub;

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

    public Amenities(){
    }

    public Amenities(Listing listing, boolean isBathtub, boolean isParkingLot, boolean isStove,
                     boolean isDoubleBed, boolean isBubblePool, boolean isCycle, boolean isSauna) {
        this.listing = listing;
        this.isBathtub = isBathtub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isCycle = isCycle;
        this.isSauna = isSauna;
    }
}
