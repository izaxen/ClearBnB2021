package dtos;

import entityDO.Listing;
import entityDO.User;

import java.util.Date;

public class AddBookingDTO {

    private String startDate;
    private String endDate;

    public AddBookingDTO() {
    }

    public AddBookingDTO(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

}
