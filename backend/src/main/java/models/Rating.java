package models;

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

    private Integer rating;

    private String message;

    public Rating(){}

    public Rating(User reviewer, User recipient, Integer rating, String message) {
        this.reviewer = reviewer;
        this.recipient = recipient;
        this.rating = rating;
        this.message = message;
    }
}
