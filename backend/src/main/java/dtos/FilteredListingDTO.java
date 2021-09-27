package dtos;

import java.sql.Timestamp;

public class FilteredListingDTO {

    private String availableStartDate;
    private String availableEndDate;

    public FilteredListingDTO() {
    }

    public FilteredListingDTO(String availableStartDate, String availableEndDate) {
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
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
}
