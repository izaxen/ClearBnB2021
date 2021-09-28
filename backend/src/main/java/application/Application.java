package application;

import express.Express;
import routes.Routes;

import java.nio.file.Paths;

public class Application {

    public Application() {
        Express app = new Express();
        Repositories repository = new Repositories();
        new Routes(app, repository);

        app.useStatic(Paths.get("backend/src/Static"));
        app.listen(4000);

    }

}
