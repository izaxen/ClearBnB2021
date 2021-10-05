package dtos;

import entityDO.Booking;
import entityDO.User;

public class CreateRatingDTO {

    User reviewer;
    User recipient;
    int rating;
    String message;
    Booking booking;
    String dateVisited;

    public CreateRatingDTO(User reviewer, User recipient, int rating, String message, Booking booking, String dateVisited) {
        this.reviewer = reviewer;
        this.recipient = recipient;
        this.rating = rating;
        this.message = message;
        this.booking = booking;
        this.dateVisited = dateVisited;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getDateVisited() {
        return dateVisited;
    }

    public void setDateVisited(String dateVisited) {
        this.dateVisited = dateVisited;
    }


}
