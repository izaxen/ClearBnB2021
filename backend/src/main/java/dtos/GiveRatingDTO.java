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
