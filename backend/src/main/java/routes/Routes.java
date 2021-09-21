package routes;

import express.Express;
import application.Repositories;
import routes.booking.BookingRoutes;
import routes.user.UserRoutes;

public class Routes {

    public Routes(Express app, Repositories repositories) {

        new UserRoutes(app, repositories.user());
        new BookingRoutes(app, repositories.booking());

    }
}
