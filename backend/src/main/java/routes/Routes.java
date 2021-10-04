package routes;

import com.mongodb.DB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import db.MongoDBClient;
import entityDO.Listing;
import express.Express;
import application.Repositories;

import java.util.Collection;

public class Routes {
    Repositories repositories;

    public Routes(Express app, Repositories repositories, MongoDatabase mongoDatabase) {

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
