package application;

import entityDO.Rating;
import express.Express;
import routes.Routes;

import java.util.List;

public class Application {

    public Application() {
        Express app = new Express();
        Repositories repository = new Repositories();
        new Routes(app, repository);

        app.listen(4000);

    }

}
