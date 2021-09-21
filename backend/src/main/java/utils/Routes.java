package utils;

import express.Express;

public class Routes {

    Routes() {

        Express app = new Express();

        app.get("/", (req, res) -> {
            models.User u = req.session("current-user");
            res.send("Hello World");
        });

        app.listen(4000); // Start server on port 4000
    }
}
