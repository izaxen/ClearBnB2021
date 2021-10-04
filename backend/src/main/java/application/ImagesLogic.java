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
    public void uploadImages(String id, List<UploadedFile> files){
        Timestamp extraId = new Timestamp(System.currentTimeMillis());

        System.out.println(files.isEmpty());

        for (UploadedFile file : files) {
            FileUtil.streamToFile(file.getContent(), "backend/src/Static/uploads/" + id + "/" + extraId.getTime()+ file.getFilename());
        }

        saveUploadedImagesToDb(id);


    }

    public void saveUploadedImagesToDb (String id){

        String[] fileNames;
        File f = new File("backend/src/Static/uploads/" + id + "/");
        fileNames = f.list();
        if (fileNames == null) {
            fileNames = new String[1];
            for (int i = 0; i < 1; i++) {
                fileNames[i] = "https://borsen.dagbladet.no/images/72078860.jpg?imageId=72078860&x=0.28222013170273&y=0&cropw=93.508936970837&croph=100&width=952&height=572&compression=80";
            }
        } else {
            for (int i = 0; i < fileNames.length; i++) {
                fileNames[i] = "/uploads/" + id + "/" + fileNames[i];
            }
        }

        int id1 = Integer.parseInt(id);
        Listing listing = repositories.listingRepository.findById(id1).get();
        //List<Image> newList = null;
        for (String file: fileNames){
            newList.add(new Image(file, listing));

            //repositories.imageRepository.addImage(new Image(file,repositories.listingRepository.findById(i).get()));
                    }
        listing.setImages(newList);
        logicHandler.listingLogic.updateListingAndCreateANewMongoDBCollection(listing);
    }




}
