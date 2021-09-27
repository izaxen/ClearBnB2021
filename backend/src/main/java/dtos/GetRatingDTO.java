package dtos;

public class GetRatingDTO {

    int rating;
    String review; //(message)
    String reviewer;
    String dateWritten;

    public GetRatingDTO(int rating, String review, String reviewer, String dateWritten) {
        this.rating = rating;
        this.review = review;
        this.reviewer = reviewer;
        this.dateWritten = dateWritten;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getDateWritten() {
        return dateWritten;
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
