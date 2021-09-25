package dtos;

import java.sql.Timestamp;

public class FilteredListingDTO {

    private Timestamp availableStartDate;
    private Timestamp availableEndDate;

    public FilteredListingDTO() {
    }

    public FilteredListingDTO(Timestamp availableStartDate, Timestamp availableEndDate) {
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
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
