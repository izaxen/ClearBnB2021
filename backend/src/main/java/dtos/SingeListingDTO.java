package dtos;

import entityDO.Image;
import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.List;

public class SingeListingDTO {

    private int id;
    private int price;
    private String description;
    private String availableStartDate;
    private String availableEndDate;
    private String city;
    private String addressListing;
    private Boolean isBathTub;
    private Boolean isParkingLot;
    private Boolean isStove;
    private Boolean isDoubleBed;
    private Boolean isBubblePool;
    private Boolean isBicycle;
    private Boolean isSauna;
    private ArrayList<String> imageslist;

    public SingeListingDTO() {
    }

    public SingeListingDTO(int id, int price, String description, String availableStartDate, String availableEndDate,
                           String city, String addressListing, Boolean isBathTub, Boolean isParkingLot, Boolean isStove,
                           Boolean isDoubleBed, Boolean isBubblePool, Boolean isBicycle, Boolean isSauna) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
        this.city = city;
        this.addressListing = addressListing;
        this.isBathTub = isBathTub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isBicycle = isBicycle;
        this.isSauna = isSauna;
    }

    public SingeListingDTO(int price, String description, String availableStartDate, String availableEndDate,
                           String city, String addressListing, Boolean isBathTub, Boolean isParkingLot, Boolean isStove,
                           Boolean isDoubleBed, Boolean isBubblePool, Boolean isBicycle, Boolean isSauna) {
        this.price = price;
        this.description = description;
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
        this.city = city;
        this.addressListing = addressListing;
        this.isBathTub = isBathTub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isBicycle = isBicycle;
        this.isSauna = isSauna;
    }

    public SingeListingDTO(int id, int price, String description, String availableStartDate, String availableEndDate,
                           String city, String addressListing, Boolean isBathTub, Boolean isParkingLot, Boolean isStove,
                           Boolean isDoubleBed, Boolean isBubblePool, Boolean isBicycle, Boolean isSauna,
                           ArrayList<String> imageslist) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
        this.city = city;
        this.addressListing = addressListing;
        this.isBathTub = isBathTub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isBicycle = isBicycle;
        this.isSauna = isSauna;
        this.imageslist = imageslist;
    }

    public ArrayList<String> getImageslist() {
        return imageslist;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getAvailableStartDate() {
        return availableStartDate;
    }

    public String getAvailableEndDate() {
        return availableEndDate;
    }

    public String getCity() {
        return city;
    }

    public String getAddressListing() {
        return addressListing;
    }

    public Boolean getBathTub() {
        return isBathTub;
    }

    public Boolean getParkingLot() {
        return isParkingLot;
    }

    public Boolean getStove() {
        return isStove;
    }

    public Boolean getDoubleBed() {
        return isDoubleBed;
    }

    public Boolean getBubblePool() {
        return isBubblePool;
    }

    public Boolean getBicycle() {
        return isBicycle;
    }

    public Boolean getSauna() {
        return isSauna;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailableStartDate(String availableStartDate) {
        this.availableStartDate = availableStartDate;
    }

    public void setAvailableEndDate(String availableEndDate) {
        this.availableEndDate = availableEndDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddressListing(String addressListing) {
        this.addressListing = addressListing;
    }

    public void setBathTub(Boolean bathTub) {
        isBathTub = bathTub;
    }

    public void setParkingLot(Boolean parkingLot) {
        isParkingLot = parkingLot;
    }

    public void setStove(Boolean stove) {
        isStove = stove;
    }

    public void setDoubleBed(Boolean doubleBed) {
        isDoubleBed = doubleBed;
    }

    public void setBubblePool(Boolean bubblePool) {
        isBubblePool = bubblePool;
    }

    public void setBicycle(Boolean bicycle) {
        isBicycle = bicycle;
    }

    public void setSauna(Boolean sauna) {
        isSauna = sauna;
    }

    public void setImageslist(ArrayList<String> imageslist) {
        this.imageslist = imageslist;
    }

    @Override
    public String toString() {
        return "SingeListingDTO{" +
                "id=" + id +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", availableStartDate='" + availableStartDate + '\'' +
                ", availableEndDate='" + availableEndDate + '\'' +
                ", city='" + city + '\'' +
                ", addressListing='" + addressListing + '\'' +
                ", isBathTub=" + isBathTub +
                ", isParkingLot=" + isParkingLot +
                ", isStove=" + isStove +
                ", isDoubleBed=" + isDoubleBed +
                ", isBubblePool=" + isBubblePool +
                ", isBicycle=" + isBicycle +
                ", isSauna=" + isSauna +
                '}';
    }
}
