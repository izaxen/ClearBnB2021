package routes;

import entityDO.Listing;
import express.Express;
import application.Repositories;

public class Routes {
    Repositories repositories;

    public Routes(Express app, Repositories repositories) {

        new UserRoutes(app, repositories.getUserRep());
        new BookingRoutes(app, repositories);
        new ListingRoutes(app, repositories);
        new AddressRoutes(app, repositories);
        new AmenityRoutes(app, repositories);
        new RatingRoutes(app, repositories);
        new ImageRoutes(app, repositories);
        new BankRoutes(app, repositories);
        new ChatRoutes(app, repositories);
    }
}
