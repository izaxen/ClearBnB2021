package dtos;

import java.sql.Timestamp;

public class AddListingDTO {
    private int price;
    private String description;
    private Timestamp availableStartDate;
    private Timestamp availableEndDate;

    public AddListingDTO() {
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
