package mapper;

import dtos.AddAmenityDTO;
import dtos.UpdateAmenityDTO;
import entityDO.Amenities;
import entityDO.Listing;

public class AmenityService {

    public Amenities convertAddAmenitiesToAmenities(AddAmenityDTO add, Listing listing){
        //Check if incoming is null convert to false
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

        return new Amenities(add.getId(), add.getBathTub(), add.getParkingLot(), add.getStove(), add.getDoubleBed(),
                add.getBubblePool(), add.getBicycle(), add.getSauna(), listing);
    }
 }
