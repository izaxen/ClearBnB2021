package application;


import com.mongodb.client.MongoCollection;
import db.MongoDBClient;
import express.Express;
import routes.Routes;

import java.net.UnknownHostException;
import java.nio.file.Paths;

public class Application {

    public Application() throws UnknownHostException {

        Express app = new Express();
        Repositories repository = new Repositories();
        MongoDBClient mongoDBClient = new MongoDBClient();
        new Routes(app, repository, mongoDBClient.getDatabase());
        MongoCollection collection = mongoDBClient.getDatabase().getCollection("cache");

        app.useStatic(Paths.get("backend/src/Static"));
        app.listen(4000);

        mongoDBClient.getMongoClient().close();

    }
}
