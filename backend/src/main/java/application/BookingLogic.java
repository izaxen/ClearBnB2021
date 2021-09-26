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

        // so he meant if there is any booking, which will return true, will show this mess.
        if(checkIfListingAlreadyIsBooked(dto, listing)){
            System.out.println("booked");
            return "Already booked";
        }
        Booking booking = new Booking(user, listing, dto.getStartDate(), dto.getEndDate());
//        System.out.println(booking);
//        System.out.println("we are in create new booking");
        // problem is here, when we r trying to add booking
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
