package application;

import dtos.AddBookingDTO;
import entityDO.Booking;
import entityDO.Listing;
import entityDO.User;

public class BookingLogic {

    Repositories repositories;

    public BookingLogic() {
    }

    public BookingLogic(Repositories repositories) {
        this.repositories = repositories;
    }

    public String createNewBooking(User user, AddBookingDTO dto, int listingID){
        Listing listing = repositories.listingRepository.findById(listingID).get();

        if(!checkIfListingHasAvailableDates(dto, listing)){
            return "Not available these dates.";
        }
        if(checkIfListingAlreadyIsBooked(dto, listing)){
            return "Already booked";
        }

        Booking booking = new Booking(user, listing, dto.getStartDate(), dto.getEndDate());
        repositories.booking().addBooking(booking);

        return "Successfully booked!";
    }

    public boolean checkIfListingAlreadyIsBooked(AddBookingDTO dto, Listing listing){

        return repositories.booking().checkIfListingIsAlreadyBooked(dto.getStartDate(), dto.getEndDate(), listing);
    }

    public boolean checkIfListingHasAvailableDates(AddBookingDTO dto, Listing listing){
        return repositories.bookingRepository.checkListingAvailableDates(dto.getStartDate(), dto.getEndDate(), listing);
    }



}
