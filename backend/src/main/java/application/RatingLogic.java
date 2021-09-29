package application;

import dtos.GetRatingDTO;
import dtos.GiveRatingDTO;
import entityDO.Booking;
import entityDO.Listing;
import entityDO.Rating;
import entityDO.User;
import service.RatingService;

import java.util.ArrayList;
import java.util.List;

public class RatingLogic {

    Repositories repositories;
    RatingService ratingService = new RatingService();

    public RatingLogic(Repositories repositories) {
        this.repositories = repositories;
    }

    public List<GetRatingDTO> getAllRatingsOfUser(int userID){

        User user = repositories.getUserRep().findUserById(userID);
        List<Rating> listOfRatings = repositories.ratingRepository.getRatingOfUser(user);

        ArrayList<GetRatingDTO> listOfRatingDTO = new ArrayList<>();

        if(listOfRatings!=null){
            listOfRatings.forEach(rating -> {
                listOfRatingDTO.add(ratingService.getRatingDTO(rating));
            });
        }

        return listOfRatingDTO;
    }

    public double getAvgRatingsOfUser(int userID){
        User user = repositories.getUserRep().findUserById(userID);
        return repositories.ratingRepository.calcAvgRatingOfUser(user);
    }

    //TODO, THIS WAY IS WAY TO UN-OPTIMIZED (better we had in user a field that was called isRenter).
    public List<GiveRatingDTO> checkIfThereIsAnyRatingToFill(int userID){

        User user = repositories.getUserRep().findUserById(userID);

        System.out.println("(1) userID: " + user.getID() + ", name: " + user.getFirstName());
        System.out.println("-------------------------------------------------------------------------------------------------------");

        List<Booking> oldBookingsOfALandlord = checkIfLandlordHasAnyOldBookings(user);
        oldBookingsOfALandlord.forEach(booking -> {
            System.out.println("(2) oldBookingsOfALandlord: bookingID:" + booking.getId());
            System.out.println("(2) oldBookingsOfALandlord: GuestID:" + booking.getUser().getID());
            System.out.println("(2) oldBookingsOfALandlord: GuestName:" + booking.getUser().getFirstName());
            System.out.println("-------------------------------------------------------------------------------------------------------");
        });

        List<Booking> oldBookingsOfAGuest = checkIfGuestHasAnyOldBookings(user);
        oldBookingsOfAGuest.forEach(booking -> {
            System.out.println("(3) oldBookingsOfAGuest: bookingID:" + booking.getId());
            System.out.println("-------------------------------------------------------------------------------------------------------");
        });

        List<GiveRatingDTO> bookingsThatMissingLandlordsRating = loopingOldBookingsToCheckIfRatingsIsMissing(oldBookingsOfALandlord, user);
        bookingsThatMissingLandlordsRating.forEach(giveRatingDTO -> {
            System.out.println("(4) bookingsThatMissingLandlordsRating: bookingID:" +giveRatingDTO.getBookingID());
            System.out.println("(4) Landlord Name: " + giveRatingDTO.getLandlordName() );
            System.out.println("(4) Landlord ID : " + giveRatingDTO.getLandlordID());
            System.out.println("(4) guest name : " + giveRatingDTO.getGuestName());
            System.out.println("(4) guest ID: " + giveRatingDTO.getGuestID() );
            System.out.println("-------------------------------------------------------------------------------------------------------");
        });

        List<GiveRatingDTO> bookingsThatMissingGuestsRating = loopingOldBookingsToCheckIfRatingsIsMissing(oldBookingsOfAGuest, user);
        bookingsThatMissingGuestsRating.forEach(giveRatingDTO -> {
            System.out.println("(5) bookingsThatMissingLandlordsRating: bookingID:" +giveRatingDTO.getBookingID());
            System.out.println("(5) Landlord Name: " + giveRatingDTO.getLandlordName() );
            System.out.println("(5) Landlord ID : " + giveRatingDTO.getLandlordID());
            System.out.println("(5) guest name : " + giveRatingDTO.getGuestName());
            System.out.println("(5) guest ID: " + giveRatingDTO.getGuestID() );
            System.out.println("-------------------------------------------------------------------------------------------------------");
        });

        /*bookingsThatMissingLandlordsRating.forEach(booking -> {
            bookingsThatUserCanAddARatingToDTO.add(new GiveRatingDTO(booking, 0, "", user, booking.getUser()));
        });

        bookingsThatMissingGuestsRating.forEach(booking -> {
            bookingsThatUserCanAddARatingToDTO.add(new GiveRatingDTO(booking, 0, "", booking.getUser(), user));
        });*/

        bookingsThatMissingLandlordsRating.addAll(bookingsThatMissingGuestsRating);

        return bookingsThatMissingLandlordsRating;
    }

    public List<Booking> checkIfLandlordHasAnyOldBookings(User user){
         List<Listing> listings = repositories.listingRepository.findAllListingsFromUser(user);
         List<Booking> bookings = new ArrayList<>();

         if(!listings.isEmpty()) {
             listings.forEach(listing -> {
                 List<Booking> x = repositories.bookingRepository.findALandlordsOldBookings(listing);
                 if (x != null) {
                     bookings.addAll(x);
                 }
             });
         }

         return bookings;
    }

    public List<Booking> checkIfGuestHasAnyOldBookings(User user){
        return repositories.booking().findAGuestsOldBookings(user);
    }

    public List<GiveRatingDTO> loopingOldBookingsToCheckIfRatingsIsMissing(List<Booking> bookings, User user){

        List<Booking> bookingsThatHasRatingsToAdd = new ArrayList<>();
        List<GiveRatingDTO> bookingsThatUserCanAddARatingToDTO = new ArrayList<>();

        bookings.forEach(booking -> {
            System.out.println("(6) loopingOldBookingsToCheckIfRatingsIsMissing: bookingID:" + booking.getId());
            System.out.println("(6) loopingOldBookingsToCheckIfRatingsIsMissing: guest id:" + booking.getUser().getID());
            System.out.println("(6) loopingOldBookingsToCheckIfRatingsIsMissing: guest id:" + booking.getUser().getFirstName());
        });

        bookings.forEach(booking -> {
            User guest = booking.getUser();
            List<Rating> ratings = repositories.ratingRepository.getRatingsLinkedToBooking(booking);
            System.out.println("How many ratings: " + ratings.size());
            User owner = repositories.getListingRepository().findOwnerOfListingWithABooking(booking);
            if(ratings.size() >= 2){
                return; //Controls if booking already has 2 ratings
            }

            if(user.getID() != booking.getUser().getID()){
                bookingsThatUserCanAddARatingToDTO.add(new GiveRatingDTO(booking.getId(), 0, "", user, guest, booking.getEndDate() ));
            }else if(user.getID() == booking.getUser().getID()){
                bookingsThatUserCanAddARatingToDTO.add(new GiveRatingDTO(booking.getId(), 0, "", owner , user, booking.getEndDate() ));
            }


        });

        /*bookingsThatHasRatingsToAdd.forEach(booking -> {
            booking.toString();
        });*/

        System.out.println("-------------------------------------------------------------------------------------------------------");

        return bookingsThatUserCanAddARatingToDTO;
    }

}
