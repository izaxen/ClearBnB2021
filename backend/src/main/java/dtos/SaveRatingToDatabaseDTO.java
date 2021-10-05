package dtos;

import entityDO.Booking;
import entityDO.User;

public class SaveRatingToDatabaseDTO {

    Booking booking;
    User reviewer;
    User recipient;
    int rating;
    String review;
    String dateVisited;

    public SaveRatingToDatabaseDTO(Booking booking, User reviewer, User recipient, int rating, String review) {
        this.booking = booking;
        this.reviewer = reviewer;
        this.recipient = recipient;
        this.rating = rating;
        this.review = review;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDateVisited() {
        return dateVisited;
    }

    public void setDateVisited(String dateVisited) {
        this.dateVisited = dateVisited;
    }

    @Override
    public String toString() {
        return "SaveRatingToDataBaseDTO{" +
                "booking=" + booking +
                ", reviewer=" + reviewer +
                ", recipient=" + recipient +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                ", dateVisited='" + dateVisited + '\'' +
                '}';
    }
}
