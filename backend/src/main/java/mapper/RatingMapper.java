package mapper;

import dtos.CreateRatingDTO;
import dtos.DeleteRatingDTO;
import dtos.GetRatingDTO;
import entityDO.Booking;
import entityDO.Rating;
import entityDO.User;

import java.awt.print.Book;

public class RatingMapper {

    public GetRatingDTO getRatingDTO(Rating rating){

        return new GetRatingDTO(
                rating.getID(),
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

    public Rating createNewRatingFromDTO(User reviewer, User recipient, Integer rating, String message, Booking booking){
        return new Rating(reviewer, recipient, rating, message, booking);
    }

    public DeleteRatingDTO deleteRatingDTO(int id){
        return new DeleteRatingDTO(id);
    }

}
