import express.Express;
import models.User;
import repositories.Repositories;
import routes.Routes;

import java.util.Optional;

public class Application {

    public Application() {
        Express app = new Express();
        Repositories repository = new Repositories();
        new Routes(app, repository);

        app.listen(4000);


    }







}
