package utils;

import express.Express;

public class Routes {

    Routes() {

        Express app = new Express();

        app.get("/", (req, res) -> {
            res.send("Hello World");
            System.out.println("called");
        });

        app.get("/api/", (req, res) -> {
            try {
                System.out.println("Sending hello world API");
                res.json("Hello World");
            }catch (Exception e){
                System.out.println(e);
            }
        });

        app.listen(4000); // Start server on port 4000
    }
}
