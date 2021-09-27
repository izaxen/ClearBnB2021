package routes;

import application.ImagesLogic;
import application.Repositories;
import entityDO.Listing;
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

        app.post("/api/uploads/", (req, res) -> { //Uploading files
            Listing listing = req.session("current-listing");
            List<UploadedFile> files = req.formDataFiles("files");
            imagesLogic.uploadImages(listing.getId().toString(), files);
            res.send("Imagesuploaded");
        });

        app.get("/api/uploads/:id", (req, res) -> {
                    String id = req.params("id");
                    res.json(imagesLogic.getUploadedImages(id));
                }
        );

    }

}
