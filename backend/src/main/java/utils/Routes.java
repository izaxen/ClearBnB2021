package utils;

import express.Express;

public class Routes {

    Routes() {

        Express app = new Express();

        app.get("/", (req, res) -> {
            res.send("Hello World");
        });

        app.get("/api/", (req, res) -> {
            res.json("Hello World");
        });

        app.listen(4000); // Start server on port 4000
    }
}
