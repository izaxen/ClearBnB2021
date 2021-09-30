package dtos;

import static java.lang.Integer.parseInt;

public class FrontendRatingDTO {

    int reviewerID;
    int recipientID;
    int bookingID;
    String rating;
    String message;

    public FrontendRatingDTO() {
    }

    public FrontendRatingDTO(int reviewerID, int recipientID, int bookingID, String rating, String message) {
        this.reviewerID = reviewerID;
        this.recipientID = recipientID;
        this.bookingID = bookingID;
        this.rating = rating;
        this.message = message;
    }

    public int getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(int reviewerID) {
        this.reviewerID = reviewerID;
    }

    public int getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(int recipientID) {
        this.recipientID = recipientID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FrontendRatingDTO{" +
                "reviewerID=" + reviewerID +
                ", recipientID=" + recipientID +
                ", bookingID=" + bookingID +
                ", rating='" + rating + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
