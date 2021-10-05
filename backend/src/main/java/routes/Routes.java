package routes;

import application.LogicHandler;
import com.mongodb.client.MongoCollection;
import express.Express;
import application.Repositories;

public class Routes {

    public Routes(Express app, Repositories repositories, MongoCollection collection) {

        LogicHandler logicHandler = new LogicHandler(repositories, collection);
        new UserRoutes(app, repositories.getUserRepository());
        new BookingRoutes(app, repositories);
        new ListingRoutes(app, repositories,collection, logicHandler);
        new AddressRoutes(app, repositories);
        new AmenityRoutes(app, repositories);
        new RatingRoutes(app, repositories);
        new ImageRoutes(app, repositories, logicHandler);
        new BankRoutes(app, repositories);
        new ChatRoutes(app, repositories);

    }
}
