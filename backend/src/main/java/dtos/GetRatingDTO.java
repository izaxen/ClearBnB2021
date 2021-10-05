package dtos;

import java.text.SimpleDateFormat;

public class GetRatingDTO {

    int id;
    int rating;
    String review; //(message)
    String reviewer;
    String dateWritten;

    public GetRatingDTO(int id, int rating, String review, String reviewer, String dateWritten) {
        this.id = id;
        this.rating = rating;
        this.review = review;
        this.reviewer = reviewer;
        this.dateWritten = dateWritten;
    }

    public int getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getDateWritten() {
        return dateWritten;
    }

    public void setDateWritten(String dateWritten) {
        this.dateWritten = dateWritten;
    }

    @Override
    public String toString() {
        return "GetRatingDTO{" +
                "Rating=" + rating +
                ", review='" + review + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", dateWritten='" + dateWritten + '\'' +
                '}';
    }
}
