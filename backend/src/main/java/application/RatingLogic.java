package application;

import dtos.GetRatingDTO;
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

        listOfRatings.forEach(rating -> {
            listOfRatingDTO.add(ratingService.getRatingDTO(rating));
        });

        return listOfRatingDTO;
    }

    public double getAvgRatingsOfUser(int userID){
        User user = repositories.getUserRep().findUserById(userID);
        return repositories.ratingRepository.calcAvgRatingOfUser(user);
    }

}
