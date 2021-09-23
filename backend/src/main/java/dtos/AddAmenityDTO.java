package dtos;

public class AddAmenityDTO {

    private boolean isBathTub;
    private boolean isParkingLot;
    private boolean isStove;
    private boolean isDoubleBed;
    private boolean isBubblePool;
    private boolean isCycle;
    private boolean isSauna;

    public AddAmenityDTO(boolean isBathTub, boolean isParkingLot, boolean isStove, boolean isDoubleBed, boolean isBubblePool, boolean isCycle, boolean isSauna) {
        this.isBathTub = isBathTub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isCycle = isCycle;
        this.isSauna = isSauna;
    }

    public AddAmenityDTO() {


    }

    public boolean isBathTub() {
        return isBathTub;
    }

    public void setBathTub(boolean bathTube) {
        isBathTub = bathTube;
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
