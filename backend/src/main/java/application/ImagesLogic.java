package application;

import entityDO.Image;
import entityDO.Listing;
import io.javalin.core.util.FileUtil;
import io.javalin.http.UploadedFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

public class ImagesLogic {
    Repositories repositories;

    public ImagesLogic(Repositories repositories) {
        this.repositories = repositories;
    }
    public void uploadImages(String id, List<UploadedFile> files){
        Timestamp extraId = new Timestamp(System.currentTimeMillis());

        for (UploadedFile file : files) {
            FileUtil.streamToFile(file.getContent(), "backend/src/Static/uploads/" + id + "/" + extraId.getTime()+ file.getFilename());
        }

        saveUploadedImagesToDb(id);
    }

    public String[] saveUploadedImagesToDb (String id){

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

        for (String file: fileNames){
            int i = Integer.parseInt(id);
            repositories.imageRepository.addImage(new Image(file,repositories.listingRepository.findById(i).get()));
                    }
        return fileNames;
    }
}
