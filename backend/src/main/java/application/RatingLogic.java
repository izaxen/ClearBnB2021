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

    //THIS WAY IS WAY TOO UN-OPTIMIZED (better we had in user a field that was called isLandlord or b). /Mac
    public List<GiveRatingDTO> checkIfThereIsAnyRatingToFill(User user){

        List<GiveRatingDTO> bookingsThatMissingLandlordsRating = null;
        
        try{
            List<Booking> oldBookings = checkIfUserHasAnyOldBookingsAndReturnThem(user);
            bookingsThatMissingLandlordsRating = loopingOldBookingsToCheckIfRatingsIsMissing(oldBookings, user);
        }catch (java.lang.NullPointerException e){
            System.out.println(e.getMessage());            
        }
        return bookingsThatMissingLandlordsRating;        
    }

    public List<Booking> checkIfUserHasAnyOldBookingsAndReturnThem(User user){
        return repositories.booking().findAGuestsOldBookings(user);
    }

    public List<GiveRatingDTO> loopingOldBookingsToCheckIfRatingsIsMissing(List<Booking> bookings, User user){

        List<GiveRatingDTO> bookingsThatUserCanAddARatingToDTO = new ArrayList<>();

        bookings.forEach(booking -> {
            User guest = booking.getUser();
            List<Rating> ratings = repositories.ratingRepository.getRatingsLinkedToBooking(booking);

            //Controls if booking already has 2 ratings or if this user already have given a rating.
            if(ratings.size() != 1){
                return;
            }
            if(ratings.get(0).getReviewer() == user){
                return;
            }

            //booking.getUser = "guest (owner_ID in booking entity in DB)"
            if(user.getID() != booking.getUser().getID()){
                bookingsThatUserCanAddARatingToDTO.add(new GiveRatingDTO(booking.getId(), 0, "", user, guest, booking.getStartDate() ));
            }else if(user.getID() == booking.getUser().getID()){
                User owner = repositories.getListingRepository().findOwnerOfListingWithABooking(booking);
                bookingsThatUserCanAddARatingToDTO.add(new GiveRatingDTO(booking.getId(), 0, "", owner , user, booking.getStartDate() ));
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
                frontendRatingDTO.getMessage());

        repositories.ratingRepository.addRating(new Rating(
                saveRatingToDataBaseDTO.getReviewer(),
                saveRatingToDataBaseDTO.getRecipient(),
                saveRatingToDataBaseDTO.getRating(),
                saveRatingToDataBaseDTO.getReview(),
                saveRatingToDataBaseDTO.getBooking()));
    }
}
