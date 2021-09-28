package application;

import dtos.GetRatingDTO;
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
    public int checkIfThereIsAnyRatingToFill(int userID){

        Booking booking = repositories.bookingRepository.findById(4).get();
        User user = repositories.getUserRep().findUserById(userID);

        checkIfRenterHasAnyOldBookings(user);

        List<Rating> list = repositories.ratingRepository.checkIfThereIsAnyRatingToFill(booking, user);

        if(list.isEmpty()){
            return 1;
        }

        list.forEach(rating -> {

        });

        return 1;
    }

    public List<Listing> checkIfRenterHasAnyOldBookings(User user){
         List<Listing> listings = repositories.listingRepository.findAllListingsFromUser(user);
         listings.forEach(listing ->{
             
         });

         return ;
    }


}
