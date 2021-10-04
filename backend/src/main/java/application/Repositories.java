package application;

import entityManager.EntityManagerCopy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositories.*;

public class Repositories {

    //Is this best praxis of having a collection class ??

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ClearBnB2021");
    jakarta.persistence.EntityManager entityManager = entityManagerFactory.createEntityManager();

    ListingRepository listingRepository = new ListingRepository(new EntityManagerCopy().getEntityManager());
    UserRepository userRepository = new UserRepository(new EntityManagerCopy().getEntityManager());
    AmenitiesRepository amenitiesRepository = new AmenitiesRepository(new EntityManagerCopy().getEntityManager());
    CurrentChatRepository currentChatRepository = new CurrentChatRepository(new EntityManagerCopy().getEntityManager());
    ChatMessageRepository chatMessageRepository = new ChatMessageRepository(new EntityManagerCopy().getEntityManager());
    RatingRepository ratingRepository = new RatingRepository(new EntityManagerCopy().getEntityManager());
    AddressRepository addressRepository = new AddressRepository(new EntityManagerCopy().getEntityManager());
    BookingRepository bookingRepository = new BookingRepository(new EntityManagerCopy().getEntityManager());
    AmenitiesRevisionRepository amenitiesRevisionRepository = new AmenitiesRevisionRepository(new EntityManagerCopy().getEntityManager());
    ListingRevisionRepository listingRevisionRepository = new ListingRevisionRepository(new EntityManagerCopy().getEntityManager());
    AddressRevisionRepository addressRevisionRepository = new AddressRevisionRepository(new EntityManagerCopy().getEntityManager());
    ImageRepository imageRepository = new ImageRepository(new EntityManagerCopy().getEntityManager());
    BankRepository bankRepository = new BankRepository(new EntityManagerCopy().getEntityManager());

    public AddressRevisionRepository getAddressRevisionRepository() {
        return addressRevisionRepository;
    }

    public ImageRepository getImageRepository() {
        return imageRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public BookingRepository getBookingRepository() {
        return bookingRepository;
    }

    public AmenitiesRevisionRepository getAmenitiesRevisionRepository() {
        return amenitiesRevisionRepository;
    }

    public ListingRevisionRepository getListingRevisionRepository() {
        return listingRevisionRepository;
    }

    public BookingRepository booking() {
        return bookingRepository;
    }

    public ListingRepository getListingRepository() {
        return listingRepository;
    }

    public UserRepository getUserRep() {
        return userRepository;
    }

    public AmenitiesRepository getAmenitiesRepository() {
        return amenitiesRepository;
    }

    public CurrentChatRepository getCurrentChatRepository() {
        return currentChatRepository;
    }

    public ChatMessageRepository getChatMessageRepository() {
        return chatMessageRepository;
    }

    public RatingRepository getRatingRepository() {
        return ratingRepository;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }
}
