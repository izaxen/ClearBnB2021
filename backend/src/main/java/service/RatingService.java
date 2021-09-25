package service;

import dtos.GetRatingDTO;
import entityDO.Rating;
import entityDO.User;

public class RatingService {

    public GetRatingDTO getRatingDTO(Rating rating){
        return new GetRatingDTO(
                rating.getRating(),
                rating.getMessage(),
                rating.getReviewer().getFirstName()
                        + " " +
                        rating.getReviewer().getSurName(),
                rating.getDateCreated() );
    }

}
