package dtos;

import entityDO.User;

public class GiveRatingDTO {

    int bookingID;
    int rating;
    String review; //(message)
    int reviewer;
    int recipient;
    String reviewerName;
    String recipientName;
    String dateVisited;

    public GiveRatingDTO(int bookingID, int rating, String review, User reviewer, User recipient, String dateVisited) {
        this.bookingID = bookingID;
        this.rating = rating;
        this.review = review;
        this.reviewer = reviewer.getId();
        this.recipient = recipient.getId();
        this.reviewerName = reviewer.getFirstName() + " " + reviewer.getSurName();
        this.recipientName = recipient.getFirstName() + " " + recipient.getSurName();
        this.dateVisited = dateVisited;
    }

    public int getRating() {
        return rating;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
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

    public int getReviewer() {
        return reviewer;
    }

    public void setReviewer(int reviewer) {
        this.reviewer = reviewer;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getDateVisited() {
        return dateVisited;
    }

    public void setDateVisited(String dateVisited) {
        this.dateVisited = dateVisited;
    }

    @Override
    public String toString() {
        return "GiveRatingDTO{" +
                "bookingID=" + bookingID +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                ", reviewer=" + reviewer +
                ", recipient=" + recipient +
                ", reviewerName='" + reviewerName + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", dateVisited='" + dateVisited + '\'' +
                '}';
    }
}
