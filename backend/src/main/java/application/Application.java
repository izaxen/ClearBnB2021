package application;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import express.Express;
import routes.Routes;
import java.nio.file.Paths;

public class Application {

    public Application() {
        Express app = new Express();
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("clearbnb");
        MongoCollection collection = database.getCollection("cache");

        new Routes(app, new Repositories(), collection);

        app.useStatic(Paths.get("backend/src/Static"));
        app.listen(4000);

    }

}
