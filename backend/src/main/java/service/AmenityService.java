package service;

import dtos.AddAmenityDTO;
import entityDO.Amenities;
import entityDO.Listing;

public class AmenityService {


    public Amenities convertAddAmenitiesToAmenities(AddAmenityDTO add, Listing listing){
        return new Amenities(listing, add.isBathTub(), add.isBubblePool(), add.isCycle(), add.isDoubleBed(),
        add.isSauna(), add.isStove(), add.isParkingLot());



    }
}
