package service;

import dtos.AddBookingDTO;
import dtos.AddListingDTO;
import entityDO.Booking;
import entityDO.Listing;
import entityDO.User;

public class BookingService {

    public Booking convertBookingDTOIntoBooking(AddBookingDTO dto, User owner, Listing listing) {
        return new Booking(owner, listing, dto.getStartDate(), dto.getEndDate());
    }

}
