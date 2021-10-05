package mapper;

import dtos.AddAmenityDTO;
import dtos.UpdateAmenityDTO;
import entityDO.Amenities;
import entityDO.Listing;

public class AmenityMapper {

    public Amenities convertAddAmenitiesToAmenities(AddAmenityDTO add, Listing listing){
        if(add.getBathTub() == null){
            add.setBathTub(false);
        }
        if(add.getSauna() == null){
            add.setSauna(false);
        }
        if(add.getBicycle() == null){
            add.setBicycle(false);
        }
        if(add.getBubblePool() == null){
            add.setBubblePool(false);
        }
        if(add.getStove()== null){
            add.setStove(false);
        }
        if(add.getParkingLot()== null){
            add.setParkingLot(false);
        }
        if(add.getDoubleBed()==null){
            add.setDoubleBed(false);
        }

        return new Amenities(add.getBathTub(), add.getParkingLot(), add.getStove(), add.getDoubleBed(),
        add.getBubblePool(), add.getBicycle()
                , add.getSauna(),listing);
    }

    public Amenities convertUpdateAmenitiesToAmenities(UpdateAmenityDTO add, Listing listing){

        return new Amenities(listing.getId(), add.getBathTub(), add.getParkingLot(), add.getStove(), add.getDoubleBed(),
                add.getBubblePool(), add.getBicycle(), add.getSauna(), listing);
    }
 }
