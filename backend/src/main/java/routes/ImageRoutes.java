package routes;

import application.ImagesLogic;
import application.LogicHandler;
import application.Repositories;
import entityDO.Listing;
import express.Express;
import io.javalin.http.UploadedFile;

import java.util.List;

public class ImageRoutes {
    Express app;
    ImagesLogic imagesLogic;

    public ImageRoutes(Express app, Repositories repositories, LogicHandler logicHandler) {
        imagesLogic = logicHandler.getImagesLogic(repositories, logicHandler);
        this.app = app;
        uploadImages();
    }

    private void uploadImages() {
        app.post("/api/uploads/", (req, res) -> {
            Listing currentlisting = req.session("current-Listing");
            if(currentlisting != null) {
                List<UploadedFile> files = req.formDataFiles("files");
                imagesLogic.uploadImages(files, currentlisting);
            }
        });
    }
}
