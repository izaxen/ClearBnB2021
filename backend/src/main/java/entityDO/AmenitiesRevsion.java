package entityDO;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class AmenitiesRevsion{

    @OneToOne
    @JoinColumn(name="listing_rev_ID")
    private ListingRevision listingRevision;
    private Boolean isBathTub;
    private Boolean isParkingLot;
    private Boolean isStove;
    private Boolean isDoubleBed;
    private Boolean isBubblePool;
    private Boolean isBicycle;
    private Boolean isSauna;

    public AmenitiesRevsion(){

    }
}
