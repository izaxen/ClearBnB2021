package routes;

import application.ImagesLogic;
import application.Repositories;
import express.Express;
import io.javalin.core.util.FileUtil;
import io.javalin.http.UploadedFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

public class ImageRoutes {
    Express app;
    Repositories repositories;
    ImagesLogic imagesLogic;

    public ImageRoutes(Express app, Repositories repositories){
        imagesLogic = new ImagesLogic(repositories);

        app.post("/api/uploads/:id", (req, res) -> { //Uploading files
            String id = req.params("id");
            List<UploadedFile> files = req.formDataFiles("files");
            imagesLogic.uploadImages(id, files);
            res.send("Imagesuploaded");
        });

        app.get("/api/uploads/:id", (req, res) -> {
                    String id = req.params("id");
                    res.json(imagesLogic.getUploadedImages(id));
                }
        );

    }

}
