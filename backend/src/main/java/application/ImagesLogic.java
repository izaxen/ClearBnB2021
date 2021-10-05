package application;
import entityDO.Image;
import entityDO.Listing;
import io.javalin.core.util.FileUtil;
import io.javalin.http.UploadedFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ImagesLogic {
    Repositories repositories;
    LogicHandler logicHandler;
    private List<Image> newList = new ArrayList<>();

    public ImagesLogic(Repositories repositories,LogicHandler logicHandler)
    {
        this.logicHandler = logicHandler;
        this.repositories = repositories;
    }

    public void uploadImages(List<UploadedFile> files, Listing listing){

        if(files.isEmpty()){
            listing.setImages(newList);
            logicHandler.listingLogic.updateListingAndCreateANewMongoDBCollection(listing);
            return;
        }

        Timestamp extraId = new Timestamp(System.currentTimeMillis());
        for (UploadedFile file : files) {
            FileUtil.streamToFile(file.getContent(), "backend/src/Static/uploads/" + listing.getId().toString() +
                    "/" + extraId.getTime()+ file.getFilename());
        }
        saveUploadedImagesToDb(listing);
    }

    public void saveUploadedImagesToDb (Listing listing){
        String[] fileNames;
        File f = new File("backend/src/Static/uploads/" + listing.getId().toString() + "/");
        fileNames = f.list();

            for (int i = 0; i < fileNames.length; i++) {
                fileNames[i] = "/uploads/" + listing.getId().toString() + "/" + fileNames[i];
            }

        for (String file: fileNames){
            newList.add(new Image(file, listing));
                    }
        listing.setImages(newList);
        logicHandler.listingLogic.updateListingAndCreateANewMongoDBCollection(listing);
    }




}
