package application;

import dtos.*;
import entityDO.Booking;
import entityDO.Rating;
import entityDO.User;
import mapper.RatingMapper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class RatingLogic {

    Repositories repositories;
    RatingMapper ratingMapper = new RatingMapper();

    public RatingLogic(Repositories repositories) {
        this.repositories = repositories;
    }

    public List<GetRatingDTO> getAllRatingsOfUser(int userID){

        User user = repositories.getUserRep().findUserById(userID);
        List<Rating> listOfRatings = repositories.ratingRepository.getRatingOfUser(user);

        ArrayList<GetRatingDTO> listOfRatingDTO = new ArrayList<>();

        if(listOfRatings!=null){
            listOfRatings.forEach(rating -> {
                listOfRatingDTO.add(ratingMapper.getRatingDTO(rating));
            });
        }

        return listOfRatingDTO;
    }

    public double getAvgRatingsOfUser(int userID){
        User user = repositories.getUserRep().findUserById(userID);
        return repositories.ratingRepository.calcAvgRatingOfUser(user);
    }

    public List<GiveRatingDTO> checkIfThereIsAnyRatingToFill(User user){
       try{
            List<Booking> oldBookings = checkIfUserHasAnyOldBookingsAndReturnThem(user);
            return getOldBookingsThatMissingTwoRatings(oldBookings, user);
        }catch (java.lang.NullPointerException e){
            System.out.println(e.getMessage());            
        }
        return null;
    }

    public List<Booking> checkIfUserHasAnyOldBookingsAndReturnThem(User user){
        repositories.entityManager.clear();
        return repositories.booking().findAGuestsOldBookings(user);
    }

    public List<GiveRatingDTO> getOldBookingsThatMissingTwoRatings(List<Booking> oldBookings, User user){

        List<GiveRatingDTO> bookingsThatUserCanAddARatingToDTO = new ArrayList<>();

        oldBookings.forEach(booking -> {
            User guest = booking.getUser();
            List<Rating> ratings = repositories.ratingRepository.getRatingsLinkedToBooking(booking);

            //Controls if booking already has 2 ratings or if this user already have given a rating.
            if(ratings.size() >= 2){
                return;
            }
            if(!ratings.isEmpty()){
                System.out.println("Not empty");
                if(ratings.get(0).getReviewer().getId() == user.getId()){
                    System.out.println("Returned!");
                    return;
                }
            }

            //booking.getUser = "guest (owner_ID in booking entity in DB)"
            if(user.getId() != booking.getUser().getId()){
                bookingsThatUserCanAddARatingToDTO.add(new GiveRatingDTO(booking.getId(), 0, "", user, guest, booking.getStartDate() ));
            }else if(user.getId() == booking.getUser().getId()){
                User owner = repositories.getListingRepository().findOwnerOfListingWithABooking(booking);
                bookingsThatUserCanAddARatingToDTO.add(new GiveRatingDTO(booking.getId(), 0, "", owner , user, booking.getStartDate() ));
            }
        });
        System.out.println(bookingsThatUserCanAddARatingToDTO.toString());
        return bookingsThatUserCanAddARatingToDTO;
    }

    public void createNewRating(createNewRatingFromFrontendDTO createNewRatingFromFrontendDTO){
        User reviewer = repositories.userRepository.findUserById(createNewRatingFromFrontendDTO.getReviewerID());
        User recipient = repositories.userRepository.findUserById(createNewRatingFromFrontendDTO.getRecipientID());
        Booking booking = repositories.booking().findById(createNewRatingFromFrontendDTO.getBookingID()).get();

        SaveRatingToDatabaseDTO saveRatingToDataBaseDTO = new SaveRatingToDatabaseDTO(
                booking,
                reviewer,
                recipient,
                parseInt(createNewRatingFromFrontendDTO.getRating()),
                createNewRatingFromFrontendDTO.getMessage());

        List<Rating> ratings = new ArrayList<>();

        ratings.add(new Rating(saveRatingToDataBaseDTO.getReviewer(),
                saveRatingToDataBaseDTO.getRecipient(),
                saveRatingToDataBaseDTO.getRating(),
                saveRatingToDataBaseDTO.getReview(),
                saveRatingToDataBaseDTO.getBooking()));

        booking.setRating(ratings);

        repositories.bookingRepository.updateBooking(booking);

        /*repositories.ratingRepository.addRating(new Rating(
                saveRatingToDataBaseDTO.getReviewer(),
                saveRatingToDataBaseDTO.getRecipient(),
                saveRatingToDataBaseDTO.getRating(),
                saveRatingToDataBaseDTO.getReview(),
                saveRatingToDataBaseDTO.getBooking()));*/
    }

    public boolean deleteRating(int id){        ;
        return repositories.ratingRepository.deleteRating(id);
    }
}
