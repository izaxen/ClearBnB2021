package routes;

import express.Express;
import application.Repositories;

public class Routes {

    public Routes(Express app, Repositories repositories) {

        new UserRoutes(app, repositories.getUserRep());
        new BookingRoutes(app, repositories);
        new ListingRoutes(app, repositories);
        new AddressRoutes(app, repositories);
        new AmenityRoutes(app, repositories);
        new RatingRoutes(app, repositories);
    }
}
