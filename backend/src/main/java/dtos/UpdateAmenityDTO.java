package dtos;

public class UpdateAmenityDTO {
    private int id;
    private Boolean isBathTub;
    private Boolean isParkingLot;
    private Boolean isStove;
    private Boolean isDoubleBed;
    private Boolean isBubblePool;
    private Boolean isBicycle;
    private Boolean isSauna;

    public UpdateAmenityDTO(){
    }

    public UpdateAmenityDTO(int id, Boolean isBathTub, Boolean isParkingLot, Boolean isStove, Boolean isDoubleBed,
                            Boolean isBubblePool, Boolean isBicycle, Boolean isSauna) {
        this.id = id;
        this.isBathTub = isBathTub;
        this.isParkingLot = isParkingLot;
        this.isStove = isStove;
        this.isDoubleBed = isDoubleBed;
        this.isBubblePool = isBubblePool;
        this.isBicycle = isBicycle;
        this.isSauna = isSauna;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getBathTub() {
        return isBathTub;
    }

    public void setBathTub(Boolean bathTub) {
        isBathTub = bathTub;
    }

    public Boolean getParkingLot() {
        return isParkingLot;
    }

    public void setParkingLot(Boolean parkingLot) {
        isParkingLot = parkingLot;
    }

    public Boolean getStove() {
        return isStove;
    }

    public void setStove(Boolean stove) {
        isStove = stove;
    }

    public Boolean getDoubleBed() {
        return isDoubleBed;
    }

    public void setDoubleBed(Boolean doubleBed) {
        isDoubleBed = doubleBed;
    }

    public Boolean getBubblePool() {
        return isBubblePool;
    }

    public void setBubblePool(Boolean bubblePool) {
        isBubblePool = bubblePool;
    }

    public Boolean getBicycle() {
        return isBicycle;
    }

    public void setBicycle(Boolean bicycle) {
        isBicycle = bicycle;
    }

    public Boolean getSauna() {
        return isSauna;
    }

    public void setSauna(Boolean sauna) {
        isSauna = sauna;
    }
}
