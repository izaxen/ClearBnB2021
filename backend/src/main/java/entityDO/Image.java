package entityDO;

import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @Column(name="image_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @ManyToOne
    private Listing listing;

    @Column(name="image_name")
    private String imageName;


}
