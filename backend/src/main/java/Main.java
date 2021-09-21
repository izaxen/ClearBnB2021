import jakarta.persistence.*;
import jakarta.persistence.Persistence;
import models.Address;
import models.Listing;
import models.ListingRevision;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repositories.AddressRepository;
import repositories.ListingRepository;
import repositories.ListingRevisionRepository;
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
        AddressRepository addressRepository = new AddressRepository(entityManager);
        ListingRevisionRepository listingRev = new ListingRevisionRepository(entityManager);
        // Create a user
//        User user = new User("Yanni","Li","odielee@hotmail.com",10000,"daitsuki");
//        Optional<User> savedUser = userRepository.addUser(user);
//        System.out.println("Saved User: " + savedUser.get());
//
//        List<User> users = userRepository.findAllUsers();
//        System.out.println("Users:");
//        users.forEach(System.out::println);

//        List<User> users = userRepository.findByFullNameQuery("Yang","Li");
//        System.out.println("Users:");
//        users.forEach(System.out::println);
//
//        Listing listing = new Listing(100,"100m to stripclub","2021-10-10","2022-10-10");
//        listingRepository.addListing(listing);
//
//        List<Listing> listings = listingRepository.findAllListings();
//        System.out.println("Users:");
//        listings.forEach(System.out::println);

//          Optional<Listing> listing = listingRepository.findById(1);
//          listing.ifPresent(System.out::println);

//        Optional<Listing> listing1 = listingRepository.findById(1);
//        System.out.println(listing1);

//        Optional<User> user1 = userRepository.findById(2);
//        User user2 = user1.get();
//        // returns an Optional
////        user1.ifPresent(System.out::println);
////        user1.ifPresent(user -> user.setFunds(100));
////        System.out.println(user1);
////        System.out.println(user1.getClass());
////        entityManager.getTransaction().begin();
////        user1.ifPresent(entityManager::merge);
////        entityManager.getTransaction().commit();
//
//        user1.ifPresent(user -> userRepository.updateUserFirstName("updatedMarcus2",2));

//        // won't work because system won't know which object to update?
//        System.out.println(user1);
//        // If it returns an optional class, i won't be able to call methods?

//        entityManager.getTransaction().begin();
//        updatedUser = entityManager.find(User.class, user1);
//        entityManager.merge(person);
        // modify person data entityManager.getTransaction().commit()


//        System.out.println(listing1.toString());
//        listing1.ifPresent(a ->{
//            a.setUser(user1);
//        });

        //userRepository.findByName("Yang");


//        Address address = new Address("Malmö","test");
//        Optional<Address> savedAddress = addressRepository.addAddress(address);
//
//
//        Listing listing = new Listing(200,"i own the stripclub","2021-10-10","2022-10-10",new Address("Malmö","test"));
//        Optional<Listing> savedListing = listingRepository.addListing(listing);
//
//        User user = new User("Ling","Long","lucifera@hotmail.com",10000,"dsadw22");
//        user.addListings(listing);
//        Optional<User> savedUser = userRepository.addUser(user);
//        System.out.println("Saved User: " + savedUser.get());
//
//        List<Listing> listings = listingRepository.findAllListings();
//        System.out.println("Listings:");
//        listings.forEach(System.out::println);

//        Listing listing = listingRepository.findById(1).get();
//        User user = userRepository.findById(1).get();
//        System.out.println(listing.toString());
//        ListingRevision listingRevision = new ListingRevision(300,"Warm House","2021-01-17","2021-09-17",listing,user);
//        listingRev.addListingRevision(listingRevision);

        System.out.println(listingRev.findAllListingRevisionsByListingID(1));

    }

}
