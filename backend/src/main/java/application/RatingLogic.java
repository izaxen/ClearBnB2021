package application;

import dtos.GetRatingDTO;
import entityDO.Booking;
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

    public List<Rating> checkIfThereIsAnyRatingToFill(){

        List<Booking> bookings = repositories.bookingRepository.findAllBookings();
        System.out.println("BOOKING ID: ----" + bookings.get(0));

        Booking booking = bookings.get(0);

        User user = repositories.getUserRep().findUserById(45);
        System.out.println("USER ID:" + user.getID());

        List<Rating> list = repositories.ratingRepository.checkIfThereIsAnyRatingToFill(booking, user);
        list.forEach(rating -> {
            System.out.println("hey!---- " + rating.toString());
        });

        return list;
    }



}
