package mapper;

import dtos.AddBookingDTO;
import entityDO.Booking;
import entityDO.Listing;
import entityDO.User;

public class BookingMapper {

    public Booking convertDTOIntoBooking(AddBookingDTO dto, User owner, Listing listing) {
        return new Booking(owner, listing, dto.getStartDate(), dto.getEndDate(),dto.getTotalPrice());
    }

}
