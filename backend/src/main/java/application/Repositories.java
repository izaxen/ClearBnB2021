package application;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositories.*;

public class Repositories {

    //Is this best praxis of having a collection class ??

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ClearBnB2021");

    ListingRepository listingRepository = new ListingRepository(entityManagerFactory);
    UserRepository userRepository = new UserRepository(entityManagerFactory);
    AmenitiesRepository amenitiesRepository = new AmenitiesRepository(entityManagerFactory);
    CurrentChatRepository currentChatRepository = new CurrentChatRepository(entityManagerFactory);
    ChatMessageRepository chatMessageRepository = new ChatMessageRepository(entityManagerFactory);
    RatingRepository ratingRepository = new RatingRepository(entityManagerFactory);
    AddressRepository addressRepository = new AddressRepository(entityManagerFactory);
    BookingRepository bookingRepository = new BookingRepository(entityManagerFactory);
    AmenitiesRevisionRepository amenitiesRevisionRepository = new AmenitiesRevisionRepository(entityManagerFactory);
    ListingRevisionRepository listingRevisionRepository = new ListingRevisionRepository(entityManagerFactory);
    AddressRevisionRepository addressRevisionRepository = new AddressRevisionRepository(entityManagerFactory);
    ImageRepository imageRepository = new ImageRepository(entityManagerFactory);
    BankRepository bankRepository = new BankRepository(entityManagerFactory);
    MongoDBRepository mongoDBRepository = new MongoDBRepository();

    public BankRepository getBankRepository() {
        return bankRepository;
    }

    public MongoDBRepository getMongoDBRepository() {
        return mongoDBRepository;
    }

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
