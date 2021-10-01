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
        User owner = listing.getUser();
        int totalPrice = dto.getTotalPrice();

        if(listing.getUser().getId() == user.getId()){
            return "You can't book on your own listing.";
        }
        if(!checkIfListingHasAvailableDates(dto, listing)){
            return "Not available these dates.";
        }
        if(checkIfListingAlreadyIsBooked(dto, listing)){
            return "Already booked";
        }
        if(!checkIfUserCanPay(user, totalPrice)){
            return "Not enough funds";
        }

        Booking booking = new Booking(user, listing, dto.getStartDate(), dto.getEndDate(),dto.getTotalPrice());
        repositories.booking().addBooking(booking);
        paymentProcess(user, totalPrice, owner);

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
            System.out.println(owner.getBankAccount().getFunds());
            System.out.println("totalPrice: " + totalPrice);
            User admin = repositories.userRepository.findUserById(5);
            System.out.println("admin: " + admin.getBankAccount().getFunds());
            System.out.println("User before: " + user.getBankAccount().getFunds());
            user.getBankAccount().setFunds(user.getBankAccount().getFunds() - totalPrice);
            System.out.println("User: " + user.getBankAccount().getFunds());

            int ownerShare = (int) (totalPrice / 1.15);
            int clearBNB = totalPrice - ownerShare;
            owner.getBankAccount().setFunds(owner.getBankAccount().getFunds() + ownerShare);
            admin.getBankAccount().setFunds(admin.getBankAccount().getFunds() + clearBNB);

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
