package utils;

import express.Express;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Book;
import models.BookRepository;
import models.User;
import models.UserRepostitory;

import java.util.List;
import java.util.Optional;

public class Routes {


    public Routes() {

        Express app = new Express();

        new Authorization(app);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("clearbnb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        BookRepository bookRepository = new BookRepository(entityManager);

        Book mackans = new Book("Mackans bok 2");

        bookRepository.save(mackans);

        Optional<Book> specificBook = bookRepository.findById(1);
        List <Book> books = bookRepository.findAll();

        books.forEach(book -> {
            System.out.println(book.getID() + "-" + book.getName());
        });


        //User user = new User("Mackan","Udd","bla@bla.com",1000,"lösen");
        //Optional<User> savedUser = userRepostitory.save(user);

      /*  List<User> users = userRepostitory.findAll();
        System.out.println("users:");
        users.forEach(System.out::println);*/
        //userRepostitory.findById(1);

        //doExampleQuery();
        //doExampleQuery1();

        app.listen(4000); // Start server on port 4000

        // Close the entity manager and associated factory
       /* entityManager.close();
        entityManagerFactory.close();*/
    }
}
