package routes;

import express.Express;
import application.Repositories;

public class Routes {

    public Routes(Express app, Repositories repositories) {

        new UserRoutes(app, repositories.getUserRep());
        new BookingRoutes(app, repositories);
        new ListingRoutes(app, repositories.getListingRepository());
        new AddressRoutes(app, repositories.getAddressRepository());
        new AmenityRoutes(app, repositories.getAmenitiesRepository());
        new RatingRoutes(app, repositories);
    }
}
