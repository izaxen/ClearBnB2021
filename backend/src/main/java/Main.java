import jakarta.persistence.*;
import jakarta.persistence.Persistence;
import models.Listing;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repositories.ListingRepository;
import repositories.UserRepository;
import utils.Application;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        // Create our entity manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ClearBnB2021");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Create our repositories
        ListingRepository listingRepository = new ListingRepository(entityManager);
        UserRepository userRepository = new UserRepository(entityManager);
        // Create a user
//        User user = new User("Yang","Li","odielee@hotmail.com",10000,"daitsuki");
//        Optional<User> savedUser = userRepository.addUser(user);
//        System.out.println("Saved User: " + savedUser.get());

//        List<User> users = userRepository.findAllUsers();
//        System.out.println("Users:");
//        users.forEach(System.out::println);

//        List<User> users = userRepository.findByFullNameQuery("Yang","Li");
//        System.out.println("Users:");
//        users.forEach(System.out::println);

        Listing listing = new Listing(8000,"100m to stripclub","2021-10-10","2022-10-10");
        listingRepository.addListing(listing);

        List<Listing> listings = listingRepository.findAllListings();
        System.out.println("Users:");
        listings.forEach(System.out::println);

//          Optional<Listing> listing = listingRepository.findById(1);
//          listing.ifPresent(System.out::println);

//        Optional<Listing> listing1 = listingRepository.findById(1);
//        Optional<User> user1 = userRepository.findById(2);
//        System.out.println(listing1.toString());
//        listing1.ifPresent(a ->{
//            a.setUser(user1);
//        });

        //userRepository.findByName("Yang");
    }

}
