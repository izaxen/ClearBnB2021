package routes;

import express.Express;
import application.Repositories;
import routes.user.UserRoutes;

public class Routes {

    public Routes(Express app, Repositories repositories) {

        new UserRoutes(app, repositories.user());

    }
}
