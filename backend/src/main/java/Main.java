import jakarta.persistence.*;
import jakarta.persistence.Persistence;
import models.Amenities;
import models.Listing;

import repositories.AmenitiesRepository;
import repositories.ListingRepository;
import repositories.UserRepository;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        // Create our entity manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ClearBnB2021");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Create our repositories
        ListingRepository listingRepository = new ListingRepository(entityManager);
        UserRepository userRepository = new UserRepository(entityManager);
        AmenitiesRepository amenitiesRepository = new AmenitiesRepository(entityManager);



        Optional<Listing> listing1 = listingRepository.findById(1);

        Listing listing = new Listing(500,"desc", "2021-10-01", "2021-11-01");
        listingRepository.addListing(listing);

        Amenities amenities = new Amenities((listing), true, true, true, true, false, false, true);
        amenitiesRepository.addAmenities(amenities);

        //userRepository.findByName("Yang");
    }

}
