package mapper;

import dtos.GetRatingDTO;
import entityDO.Rating;

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
