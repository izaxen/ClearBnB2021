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

    public int getBookingID() {
        return bookingID;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public int getReviewer() {
        return reviewer;
    }

    public int getRecipient() {
        return recipient;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getDateVisited() {
        return dateVisited;
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
