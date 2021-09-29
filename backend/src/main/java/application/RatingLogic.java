package application;

import dtos.FrontendRatingDTO;
import dtos.GetRatingDTO;
import dtos.GiveRatingDTO;
import dtos.SaveRatingToDataBaseDTO;
import entityDO.Booking;
import entityDO.Listing;
import entityDO.Rating;
import entityDO.User;
import mapper.RatingService;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

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

    //TODO, THIS WAY IS WAY TOO UN-OPTIMIZED (better we had in user a field that was called isLandlord or b).
    public List<GiveRatingDTO> checkIfThereIsAnyRatingToFill(int userID){

        User user = repositories.getUserRep().findUserById(userID);

        List<Booking> oldBookingsOfALandlord = checkIfLandlordHasAnyOldBookings(user);
        List<Booking> oldBookingsOfAGuest = checkIfGuestHasAnyOldBookings(user);

        List<GiveRatingDTO> bookingsThatMissingLandlordsRating = loopingOldBookingsToCheckIfRatingsIsMissing(oldBookingsOfALandlord, user);
        List<GiveRatingDTO> bookingsThatMissingGuestsRating = loopingOldBookingsToCheckIfRatingsIsMissing(oldBookingsOfAGuest, user);

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

        List<GiveRatingDTO> bookingsThatUserCanAddARatingToDTO = new ArrayList<>();

        bookings.forEach(booking -> {
            User guest = booking.getUser();
            List<Rating> ratings = repositories.ratingRepository.getRatingsLinkedToBooking(booking);
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

        return bookingsThatUserCanAddARatingToDTO;
    }

    public void createNewRating(FrontendRatingDTO frontendRatingDTO){
        User reviewer = repositories.userRepository.findUserById(frontendRatingDTO.getReviewerID());
        User recipient = repositories.userRepository.findUserById(frontendRatingDTO.getRecipientID());
        Booking booking = repositories.booking().findById(frontendRatingDTO.getBookingID()).get();

        SaveRatingToDataBaseDTO saveRatingToDataBaseDTO = new SaveRatingToDataBaseDTO(
                booking,
                reviewer,
                recipient,
                parseInt(frontendRatingDTO.getRating()),
                frontendRatingDTO.getMessage(),
                frontendRatingDTO.getDateVisited());

        repositories.ratingRepository.addRating(new Rating(
                saveRatingToDataBaseDTO.getReviewer(),
                saveRatingToDataBaseDTO.getRecipient(),
                saveRatingToDataBaseDTO.getRating(),
                saveRatingToDataBaseDTO.getReview(),
                saveRatingToDataBaseDTO.getBooking(),
                saveRatingToDataBaseDTO.getDateVisited()));
    }
}
