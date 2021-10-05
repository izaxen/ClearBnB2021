package entityDO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name="rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="rating_ID")
    private int ID;

    private int rating;

    private String message;

    @Column(name="date_created")
    private String dateCreated;

    @ManyToOne
    @JoinColumn(name="reviewer_ID")
    private User reviewer;

    @ManyToOne
    @JoinColumn(name="recipient_ID")
    private User recipient;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="booking_ID")
    private Booking booking;

    public Rating(){}

    public Rating(User reviewer, User recipient, int rating, String message, Booking booking) {
        this.reviewer = reviewer;
        this.recipient = recipient;
        this.rating = rating;
        this.message = message;
        this.booking = booking;
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateCreated = date.format(myFormatObj);
    }

    public int getID() {
        return ID;
    }

    public User getReviewer() {
        return reviewer;
    }

    public User getRecipient() {
        return recipient;
    }

    public int getRating() {
        return rating;
    }

    public String getMessage() {
        return message;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void String(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Booking getBooking() {
        return booking;
    }



    @Override
    public String toString() {
        return "Rating{" +
                "ID=" + ID +
                ", rating=" + rating +
                ", message='" + message + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }
}
