package dtos;

import java.sql.Timestamp;

public class FilteredListingDTO {

    private Timestamp selectedStartDate;
    private Timestamp selectedEndDate;

    public FilteredListingDTO() {
    }

    public FilteredListingDTO(Timestamp selectedStartDate, Timestamp selectedEndDate) {
        this.selectedStartDate = selectedStartDate;
        this.selectedEndDate = selectedEndDate;
    }

    public Timestamp getSelectedStartDate() {
        return selectedStartDate;
    }

    public void setSelectedStartDate(Timestamp selectedStartDate) {
        this.selectedStartDate = selectedStartDate;
    }

    public Timestamp getSelectedEndDate() {
        return selectedEndDate;
    }

    public void setSelectedEndDate(Timestamp selectedEndDate) {
        this.selectedEndDate = selectedEndDate;
    }
}
