package routes;

import express.Express;
import repositories.Repositories;
import routes.user.Authorization;
import routes.user.UserRoutes;

public class Routes {

    public Routes(Express app, Repositories repositories) {

        new UserRoutes(app, repositories);
        new Authorization(app, repositories.getUser());

    }
}
