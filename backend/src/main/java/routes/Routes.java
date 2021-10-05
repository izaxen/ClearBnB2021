package routes;

import application.LogicHandler;
import com.mongodb.client.MongoCollection;
import express.Express;
import application.Repositories;

public class Routes {

    public Routes(Express app, Repositories repositories, MongoCollection collection) {

        LogicHandler logicHandler = new LogicHandler(repositories, collection);
        new UserRoutes(app, logicHandler);
        new BookingRoutes(app, repositories);
        new ListingRoutes(app, collection, logicHandler);
        new AddressRoutes(app, logicHandler);
        new AmenityRoutes(app, logicHandler);
        new RatingRoutes(app, repositories);
        new ImageRoutes(app, repositories, logicHandler);
        new BankRoutes(app, logicHandler);
        new ChatRoutes(app, logicHandler);

    }
}
