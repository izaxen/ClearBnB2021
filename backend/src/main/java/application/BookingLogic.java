package application;

import dtos.AddBookingDTO;
import entityDO.Booking;
import entityDO.Listing;
import entityDO.User;
import repositories.BookingRepository;
import repositories.ListingRepository;

public class BookingLogic {

    Repositories repositories;

    public BookingLogic() {
    }

    public BookingLogic(Repositories repositories) {
        this.repositories = repositories;
    }

    public void createNewBooking(User user, AddBookingDTO dto, int listingID){
        Listing listing = repositories.listingRepository.findById(listingID).get();
        Booking booking = new Booking(user, listing, dto.getStartDate(), dto.getEndDate());

        repositories.booking().addBooking(booking);
    }

    public void createNewBooking1(User user, AddBookingDTO dto, Listing listing){

        Booking booking = new Booking(user, listing, dto.getStartDate(), dto.getEndDate());

        repositories.booking().addBooking(booking);
    }

}
