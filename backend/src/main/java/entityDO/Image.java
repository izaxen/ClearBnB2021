package entityDO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="image_name")
    private String imageName;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="listing_ID")
    private Listing listing;

    public Image(int id, String imageName, Listing listing) {
        this.id = id;
        this.imageName = imageName;
        this.listing = listing;
    }

    public Image(String imageName, Listing listing) {
        this.imageName = imageName;
        this.listing = listing;
    }

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }
}
