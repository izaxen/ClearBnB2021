package dtos;

public class AddBookingDTO {

    private String startDate;
    private String endDate;
    private int totalPrice;
    private int listingID;

    public AddBookingDTO() {
    }

    public AddBookingDTO(String startDate, String endDate, int totalPrice, int listingID) {
        this.listingID = listingID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getListingID() {
        return listingID;
    }

    public void setListingID(int listingID) {
        this.listingID = listingID;
    }
}
