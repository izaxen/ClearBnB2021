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
