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
        if(checkIfListingAlreadyIsBooked(dto, listing)){
            return "Already booked";
        }
        Booking booking = new Booking(user, listing, dto.getStartDate(), dto.getEndDate());

        repositories.booking().addBooking(booking);
        return "Successfully booked!";
    }

    //TODO FUNCTION ON HOLD
  /*  public void createNewBooking1(User user, AddBookingDTO dto, Listing listing){

        Booking booking = new Booking(user, listing, dto.getStartDate(), dto.getEndDate());

        repositories.booking().addBooking(booking);
    }*/

    public boolean checkIfListingAlreadyIsBooked(AddBookingDTO dto, Listing listing){
        return repositories.booking().checkIfListingIsAlreadyBooked(dto.getStartDate(), dto.getEndDate(), listing);
    }

}
