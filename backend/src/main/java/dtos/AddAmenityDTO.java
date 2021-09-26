package dtos;

public class AddAmenityDTO {

    private Boolean isBathTub;
    private Boolean isParkingLot;
    private Boolean isStove;
    private Boolean isDoubleBed;
    private Boolean isBubblePool;
    private Boolean isBicycle;
    private Boolean isSauna;

    public AddAmenityDTO(Boolean isBathTub, Boolean isParkingLot, Boolean isStove, Boolean isDoubleBed, Boolean isBubblePool, Boolean isBicycle, Boolean isSauna) {
        this.isBathTub = isBathTub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isBicycle = isBicycle;
        this.isSauna = isSauna;
    }

    public AddAmenityDTO() {


    }

    public Boolean isBathTub() {
        return isBathTub;
    }

    public void setBathTub(Boolean bathTube) {
        isBathTub = bathTube;
    }

    public Boolean isParkingLot() {
        return isParkingLot;
    }

    public void setParkingLot(Boolean parkingLot) {
        isParkingLot = parkingLot;
    }

    public Boolean isStove() {
        return isStove;
    }

    public void setStove(Boolean stove) {
        isStove = stove;
    }

    public Boolean isDoubleBed() {
        return isDoubleBed;
    }

    public void setDoubleBed(Boolean doubleBed) {
        isDoubleBed = doubleBed;
    }

    public Boolean isBubblePool() {
        return isBubblePool;
    }

    public void setBubblePool(Boolean bubblePool) {
        isBubblePool = bubblePool;
    }

    public Boolean isBicycle() {
        return isBicycle;
    }

    public void setIsBicycle(Boolean cycle) {
        isBicycle = cycle;
    }

    public Boolean isSauna() {
        return isSauna;
    }

    public void setSauna(Boolean sauna) {
        isSauna = sauna;
    }
}
