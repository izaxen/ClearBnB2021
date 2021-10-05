package dtos;

public class ListingFilterDTO {
    private int price;
    private String availableStartDate;
    private String availableEndDate;
    private Boolean isBathTub;
    private Boolean isParkingLot;
    private Boolean isStove;
    private Boolean isDoubleBed;
    private Boolean isBubblePool;
    private Boolean isBicycle;
    private Boolean isSauna;

    public ListingFilterDTO() {
    }

    public int getPrice() {
        return price;
    }
    public String getAvailableStartDate() {
        return availableStartDate;
    }
    public String getAvailableEndDate() {return availableEndDate;}
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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAvailableStartDate(String availableStartDate) {
        this.availableStartDate = availableStartDate;
    }

    public void setAvailableEndDate(String availableEndDate) {
        this.availableEndDate = availableEndDate;
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

    @Override
    public String toString() {
        return "ListingFilterDTO{" +
                "price=" + price +
                ", availableStartDate='" + availableStartDate + '\'' +
                ", availableEndDate='" + availableEndDate + '\'' +
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
