package application;

import com.mongodb.client.MongoCollection;

public class LogicHandler {

    ListingLogic listingLogic;
    ImagesLogic imagesLogic;
    CacheLogic cacheLogic;
    public LogicHandler(Repositories repositories, MongoCollection collection){

        this.listingLogic = new ListingLogic(repositories,collection);
        this.imagesLogic = new ImagesLogic(repositories, this);
        this.cacheLogic = new CacheLogic(collection, repositories, this);

    }

    public ListingLogic getListingLogic() {
        return listingLogic;
    }

    public void setListingLogic(ListingLogic listingLogic) {
        this.listingLogic = listingLogic;
    }

    public ImagesLogic getImagesLogic(Repositories repositories, LogicHandler logicHandler) {
        return imagesLogic;
    }

    public void setImagesLogic(ImagesLogic imagesLogic) {
        this.imagesLogic = imagesLogic;
    }

    public ListingLogic getListingLogic(Repositories repositories, MongoCollection collection) {
        return listingLogic;
    }

}
