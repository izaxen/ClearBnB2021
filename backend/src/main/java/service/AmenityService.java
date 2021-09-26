package service;

import dtos.AddAmenityDTO;
import entityDO.Amenities;
import entityDO.Listing;

public class AmenityService {

    public Amenities convertAddAmenitiesToAmenities(AddAmenityDTO add, Listing listing){
        return new Amenities(listing, add.isBathTub(), add.isParkingLot(), add.isStove(), add.isDoubleBed(),
        add.isBubblePool(), add.isBicycle(), add.isSauna());
    }
 }
