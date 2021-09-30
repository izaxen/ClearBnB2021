package mapper;

import dtos.CreateRatingDTO;
import dtos.GetRatingDTO;
import entityDO.Booking;
import entityDO.Rating;
import entityDO.User;

import java.awt.print.Book;

public class RatingService {

    public GetRatingDTO getRatingDTO(Rating rating){
        return new GetRatingDTO(
                rating.getRating(),
                rating.getMessage(),
                rating.getReviewer().getFirstName()
                        + " " +
                        rating.getReviewer().getSurName(),
                rating.getDateCreated());
    }

    public CreateRatingDTO createRatingDTO(User reviewer, User recipient, int rating, String message, Booking booking, String dateVisited){
        return new CreateRatingDTO(reviewer, recipient, rating, message, booking, dateVisited);
    }

}
