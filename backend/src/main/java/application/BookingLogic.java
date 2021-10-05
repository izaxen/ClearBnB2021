package application;

import dtos.AddBookingDTO;
import entityDO.Booking;
import entityDO.Listing;
import entityDO.User;
import mapper.BookingMapper;

public class BookingLogic {

    Repositories repositories;
    BookingMapper bookingMapper = new BookingMapper();

    public BookingLogic() {
    }

    public BookingLogic(Repositories repositories) {
        this.repositories = repositories;
    }

    public String createNewBooking(User user, AddBookingDTO dto){

        Listing listing = repositories.listingRepository.findById(dto.getListingID()).get();
        User owner = listing.getUser();

        if(listing.getUser().getId() == user.getId()){
            return "You can't book on your own listing.";
        }
        if(!checkIfListingHasAvailableDates(dto, listing)){
            return "Not available these dates.";
        }
        if(checkIfListingAlreadyIsBooked(dto, listing)){
            return "Already booked";
        }
        if(!checkIfUserCanPay(user, dto.getTotalPrice())){
            return "Not enough funds";
        }

        Booking booking = bookingMapper.convertBookingDTOIntoBooking(dto, user, listing);
        paymentProcess(user, dto.getTotalPrice(), owner);
        repositories.getBookingRepository().addBooking(booking);

        return "Successfully booked!";
    }

    public boolean checkIfListingAlreadyIsBooked(AddBookingDTO dto, Listing listing){
        return repositories.booking().checkIfListingIsAlreadyBooked(dto.getStartDate(), dto.getEndDate(), listing);
    }

    public boolean checkIfListingHasAvailableDates(AddBookingDTO dto, Listing listing){
        return repositories.bookingRepository.checkListingAvailableDates(dto.getStartDate(), dto.getEndDate(), listing);
    }

    public boolean checkIfUserCanPay(User user, int totalPrice){
        System.out.println("User: " + user);
        System.out.println("Bank: " + user.getBankAccount());
        return user.getBankAccount().getFunds() >= totalPrice;
    }

    public void paymentProcess(User user, int totalPrice, User owner){
        if(user.getId() != owner.getId()) {

            User admin = repositories.userRepository.findUserById(91);

            user.getBankAccount().setFunds(user.getBankAccount().getFunds() - totalPrice);


            int ownerShare = (int) (totalPrice / 1.15);
            int clearBNB = totalPrice - ownerShare;
            owner.getBankAccount().setFunds(owner.getBankAccount().getFunds() + ownerShare);
            admin.getBankAccount().setFunds(admin.getBankAccount().getFunds() + clearBNB);

            System.out.println("User: " + user);
            System.out.println("Owner: " + owner);
            System.out.println("admin: " + admin);

            repositories.userRepository.update(user);
            repositories.userRepository.update(owner);
            repositories.userRepository.update(admin);

            System.out.println(owner.getBankAccount().getFunds());
            System.out.println("Guest paid: " + totalPrice);
            System.out.println("Owner gets: " + ownerShare);
            System.out.println("ClearBnB gets: " + clearBNB);
        }
        else{
            System.out.println("You can't book on your own listing!");
        }




    }



}
