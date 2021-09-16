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

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("clearbnb");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        UserRepostitory userRepostitory = new UserRepostitory(entityManager);

        BookRepository bookRepository = new BookRepository(entityManager);

        Book mackans = new Book("Mackans bok 2");

        bookRepository.save(mackans);

        Optional<Book> specificBook = bookRepository.findById(1);
        List <Book> books = bookRepository.findAll();

        books.forEach(book -> {
            System.out.println(book.getID() + "-" + book.getName());
        });

        //doExampleQuery();
        //doExampleQuery1();

        new Authorization(app, userRepostitory);


        app.get("/api/", (req, res) -> {
            res.json("Hello World");
        });

        app.listen(4000); // Start server on port 4000

        // Close the entity manager and associated factory
       /* entityManager.close();
        entityManagerFactory.close();*/
    }
}
