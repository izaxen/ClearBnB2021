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
    private String address;
    private String city;


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
        this.city = listing.getAddress().getCity();
        this.address = listing.getAddress().getAddressListing();
    }

    public AddListingDTO(Integer price, String description, Timestamp availableStartDate, Timestamp availableEndDate) {
        this.price = price;
        this.description = description;
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
    }


    public int getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
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

    @Override
    public String toString() {
        return "AddListingDTO{" +
                "price=" + price +
                ", description='" + description + '\'' +
                ", availableStartDate=" + availableStartDate +
                ", availableEndDate=" + availableEndDate +
                ", userID=" + userID +
                ", userEmail='" + userEmail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
