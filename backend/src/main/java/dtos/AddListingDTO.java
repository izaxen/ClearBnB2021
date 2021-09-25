package dtos;

import entityDO.Listing;
import entityDO.User;

import java.sql.Timestamp;
import java.util.List;

public class AddListingDTO {
    private int price;
    private String description;
    private Timestamp availableStartDate;
    private Timestamp availableEndDate;
    private int userID;
    private String userEmail;
    private String firstName;


    public AddListingDTO() {
    }

    public AddListingDTO(Listing listing) {
        this.price = listing.getPrice();
        this.description = listing.getDescription();
        this.availableStartDate = listing.getAvailableStartDate();
        this.availableEndDate = listing.getAvailableEndDate();
        this.userID = listing.getUser().getID();
        this.userEmail = listing.getUser().getEmail();
        this.firstName = listing.getUser().getFirstName();
    }

    public AddListingDTO(Integer price, String description, Timestamp availableStartDate, Timestamp availableEndDate) {
        this.price = price;
        this.description = description;
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
    }





    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getAvailableStartDate() {
        return availableStartDate;
    }

    public void setAvailableStartDate(Timestamp availableStartDate) {
        this.availableStartDate = availableStartDate;
    }

    public Timestamp getAvailableEndDate() {
        return availableEndDate;
    }

    public void setAvailableEndDate(Timestamp availableEndDate) {
        this.availableEndDate = availableEndDate;
    }
}
