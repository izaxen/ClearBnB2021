package dtos;

public class UpdateListingDTO {
    private int id;
    private int price;
    private String description;
    private String availableStartDate;
    private String availableEndDate;

    public UpdateListingDTO(int id, int price, String description, String availableStartDate, String availableEndDate) {
        this.id = id;
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

    public String getAvailableStartDate() {
        return availableStartDate;
    }

    public void setAvailableStartDate(String availableStartDate) {
        this.availableStartDate = availableStartDate;
    }

    public String getAvailableEndDate() {
        return availableEndDate;
    }

    public void setAvailableEndDate(String availableEndDate) {
        this.availableEndDate = availableEndDate;
    }

    public UpdateListingDTO(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
