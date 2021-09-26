package filter;

import java.sql.Timestamp;

public class ListingFilter {
    private int price;
    private String availableStartDate;
    private String availableEndDate;
    private boolean isBathTub;
    private boolean isParkingLot;
    private boolean isStove;
    private boolean isDoubleBed;
    private boolean isBubblePool;
    private boolean isCycle;
    private boolean isSauna;

    public ListingFilter() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public boolean isBathTub() {
        return isBathTub;
    }

    public void setBathTub(boolean bathTub) {
        isBathTub = bathTub;
    }

    public boolean isParkingLot() {
        return isParkingLot;
    }

    public void setParkingLot(boolean parkingLot) {
        isParkingLot = parkingLot;
    }

    public boolean isStove() {
        return isStove;
    }

    public void setStove(boolean stove) {
        isStove = stove;
    }

    public boolean isDoubleBed() {
        return isDoubleBed;
    }

    public void setDoubleBed(boolean doubleBed) {
        isDoubleBed = doubleBed;
    }

    public boolean isBubblePool() {
        return isBubblePool;
    }

    public void setBubblePool(boolean bubblePool) {
        isBubblePool = bubblePool;
    }

    public boolean isCycle() {
        return isCycle;
    }

    public void setCycle(boolean cycle) {
        isCycle = cycle;
    }

    public boolean isSauna() {
        return isSauna;
    }

    public void setSauna(boolean sauna) {
        isSauna = sauna;
    }
}
