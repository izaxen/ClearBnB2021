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

        List<Booking> oldBookingsOfALandlord = checkIfLandlordHasAnyOldBookings(user);
        List<Booking> oldBookingsOfAGuest = checkIfGuestHasAnyOldBookings(user);

        List<GiveRatingDTO> bookingsThatMissingLandlordsRating = loopingOldBookingsToCheckIfRatingsIsMissing(oldBookingsOfALandlord, user);
        List<GiveRatingDTO> bookingsThatMissingGuestsRating = loopingOldBookingsToCheckIfRatingsIsMissing(oldBookingsOfAGuest, user);

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
            List<Rating> ratings = repositories.ratingRepository.getRatingsLinkedToBooking(booking);
            User owner = repositories.getListingRepository().findOwnerOfListingWithABooking(booking);
            if(ratings.size() >= 2){
                return; //Controls if booking already has 2 ratings
            }
            ratings.forEach(rating -> {
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.println("booking.getUser: " + booking.getUser().getSurName() + ", ID: " + booking.getUser().getID());
                System.out.println("booking.User: " + user.getSurName() + ", ID: " + user.getID());
                System.out.println("rating Reviewer: " + rating.getReviewer().getSurName() + ", ID: " + rating.getReviewer().getID());
                System.out.println("rating recipient: " + rating.getRecipient().getSurName() + ", ID: " + rating.getRecipient().getID());
                System.out.println("Listing owner: " + owner.getFirstName() + ", ID: " + owner.getID());
                if(booking.getUser().getID() == owner.getID()){
                    bookingsThatUserCanAddARatingToDTO.add(new GiveRatingDTO(
                            booking,
                            0,
                            "",
                            owner,
                            booking.getUser(),
                            booking.getEndDate()));
                }else if(booking.getUser().getID() != owner.getID()){
                    bookingsThatUserCanAddARatingToDTO.add(new GiveRatingDTO(
                            booking,
                            0,
                            "",
                            owner,
                            user,
                            booking.getEndDate()));
                }

            });
        });

        /*bookingsThatHasRatingsToAdd.forEach(booking -> {
            booking.toString();
        });*/

        return bookingsThatUserCanAddARatingToDTO;
    }

}
