package models;

import jakarta.persistence.*;

public class AmenitiesRevision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean bathtub;
    private Boolean parkinglot;
    private Boolean stove;

    @Column(name="double_bed")
    private Boolean doubleBed;

    private

    @OneToOne
    @JoinColumn(name="listing_rev_ID")
    private ListingRevision listingRevision;
}
