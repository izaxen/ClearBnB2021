package dtos;

import entityDO.Booking;
import entityDO.User;

public class GiveRatingDTO {

    int booking;
    int rating;
    String review; //(message)
    int landlordID;
    int guestID;
    String landlordName;
    String guestName;
    String dateVisited;

    public GiveRatingDTO(Booking booking, int rating, String review, User landlord, User guestName, String dateVisited) {
        this.booking = booking.getId();
        this.rating = rating;
        this.review = review;
        this.landlordID = landlord.getID();
        this.guestID = guestName.getID();
        this.landlordName = landlord.getFirstName() + " " + landlord.getSurName();
        this.guestName = guestName.getFirstName() + " " + guestName.getSurName();
        this.dateVisited = dateVisited;
    }

    public int getBooking() {
        return booking;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public int getLandlordID() {
        return landlordID;
    }

    public int getGuestID() {
        return guestID;
    }

    public String getLandlordName() {
        return landlordName;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getDateVisited() {
        return dateVisited;
    }

    @Override
    public String toString() {
        return "GiveRatingDTO{" +
                "booking=" + booking +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                ", landlordID=" + landlordID +
                ", guestID=" + guestID +
                ", landlordName='" + landlordName + '\'' +
                ", guestName='" + guestName + '\'' +
                ", dateVisited='" + dateVisited + '\'' +
                '}';
    }
}
