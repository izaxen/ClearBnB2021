package routes;

import application.ImagesLogic;
import application.LogicHandler;
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
    LogicHandler logicHandler;

    public ImageRoutes(Express app, Repositories repositories, LogicHandler logicHandler){
        imagesLogic = logicHandler.getImagesLogic(repositories, logicHandler);
        this.app = app;

        app.post("/api/uploads/", (req, res) -> { //Uploading files
            Listing currentlisting = req.session("current-Listing");
            List<UploadedFile> files = req.formDataFiles("files");

            imagesLogic.uploadImages(files, currentlisting);


            res.send("Imagesuploaded");
        });

        app.get("/api/uploads/:id", (req, res) -> {
                    String id = req.params("id");
                    //
            // res.json(imagesLogic.UploadedImages(id));
                }
        );

    }

}
