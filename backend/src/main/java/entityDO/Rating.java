package entityDO;

import jakarta.persistence.*;

@Entity
@Table(name="rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="rating_ID")
    private Integer ID;

    @ManyToOne
    @JoinColumn(name="reviewer_ID")
    private User reviewer;

    @ManyToOne
    @JoinColumn(name="recipient_ID")
    private User recipient;

    @ManyToOne
    @JoinColumn(name="booking_ID")
    private Booking booking;

    private Integer rating;

    private String message;

    @Column(name="date_created")
    private String dateCreated;

    public Rating(){}

    public Rating(User reviewer, User recipient, Integer rating, String message, Booking booking) {
        this.reviewer = reviewer;
        this.recipient = recipient;
        this.rating = rating;
        this.message = message;
        this.booking = booking;
    }

    public Integer getID() {
        return ID;
    }

    public User getReviewer() {
        return reviewer;
    }

    public User getRecipient() {
        return recipient;
    }

    public Integer getRating() {
        return rating;
    }

    public String getMessage() {
        return message;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Booking getBooking() {
        return booking;
    }
}
