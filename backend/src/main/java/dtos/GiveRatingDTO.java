package dtos;

import entityDO.Booking;
import entityDO.User;

public class GiveRatingDTO {

    Booking booking;
    int rating;
    String review; //(message)
    User reviewer;
    User recipient;


    public GiveRatingDTO(Booking booking, int rating, String review, User reviewer, User recipient) {
        this.booking = booking;
        this.rating = rating;
        this.review = review;
        this.reviewer = reviewer;
        this.recipient = recipient;
    }

    public Booking getBooking() {
        return booking;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public User getReviewer() {
        return reviewer;
    }

    public User getRecipient() {
        return recipient;
    }

    @Override
    public String toString() {
        return "GiveRatingDTO{" +
                "bookingID=" + booking +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                ", reviewer=" + reviewer +
                ", recipient=" + recipient +
                '}';
    }
}
