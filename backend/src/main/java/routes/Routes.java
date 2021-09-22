package routes;

import express.Express;
import application.Repositories;
import repositories.AmenitiesRepository;
import routes.address.AddressRoutes;
import routes.amenity.AmenityRoutes;
import routes.booking.BookingRoutes;
import routes.listing.ListingRoutes;
import routes.user.UserRoutes;

public class Routes {

    public Routes(Express app, Repositories repositories) {

        new UserRoutes(app, repositories.user());
        new BookingRoutes(app, repositories.booking());
        new ListingRoutes(app, repositories.getListingRepository());
        new AddressRoutes(app, repositories.getAddressRepository());
        new AmenityRoutes(app, repositories.getAmenitiesRepository());
    }
}
