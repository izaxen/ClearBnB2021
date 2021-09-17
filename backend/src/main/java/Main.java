import jakarta.persistence.*;
import jakarta.persistence.Persistence;
import models.Listing;

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

        Optional<Listing> listing1 = listingRepository.findById(1);


        //userRepository.findByName("Yang");
    }

}
