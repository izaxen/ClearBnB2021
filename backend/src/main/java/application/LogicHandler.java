package application;

import com.mongodb.client.MongoCollection;

public class LogicHandler {

    AddressLogic addressLogic;
    AmenityLogic amenityLogic;
    BankLogic bankLogic;
    BookingLogic bookingLogic;
    CacheLogic cacheLogic;
    ChatLogic chatLogic;
    ChatMessageLogic chatMessageLogic;
    ImagesLogic imagesLogic;
    ListingLogic listingLogic;
    RatingLogic ratingLogic;
    UserLogic userLogic;

    public LogicHandler(Repositories repositories, MongoCollection collection){

        this.addressLogic = new AddressLogic(repositories);
        this.amenityLogic = new AmenityLogic(repositories);
        this.bankLogic = new BankLogic(repositories);
        this.bookingLogic = new BookingLogic(repositories);
        this.chatLogic = new ChatLogic(repositories, this);
        this.chatMessageLogic = new ChatMessageLogic(repositories);
        this.ratingLogic = new RatingLogic(repositories);
        this.userLogic = new UserLogic(repositories.getUserRepository());
        this.listingLogic = new ListingLogic(collection,repositories, this);
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

    public AddressLogic getAddressLogic() {
        return addressLogic;
    }

    public void setAddressLogic(AddressLogic addressLogic) {
        this.addressLogic = addressLogic;
    }

    public AmenityLogic getAmenityLogic() {
        return amenityLogic;
    }

    public void setAmenityLogic(AmenityLogic amenityLogic) {
        this.amenityLogic = amenityLogic;
    }

    public BankLogic getBankLogic() {
        return bankLogic;
    }

    public void setBankLogic(BankLogic bankLogic) {
        this.bankLogic = bankLogic;
    }

    public BookingLogic getBookingLogic() {
        return bookingLogic;
    }

    public void setBookingLogic(BookingLogic bookingLogic) {
        this.bookingLogic = bookingLogic;
    }

    public CacheLogic getCacheLogic() {
        return cacheLogic;
    }

    public void setCacheLogic(CacheLogic cacheLogic) {
        this.cacheLogic = cacheLogic;
    }

    public ChatLogic getChatLogic() {
        return chatLogic;
    }

    public void setChatLogic(ChatLogic chatLogic) {
        this.chatLogic = chatLogic;
    }

    public ChatMessageLogic getChatMessageLogic() {
        return chatMessageLogic;
    }

    public void setChatMessageLogic(ChatMessageLogic chatMessageLogic) {
        this.chatMessageLogic = chatMessageLogic;
    }

    public ImagesLogic getImagesLogic() {
        return imagesLogic;
    }

    public RatingLogic getRatingLogic() {
        return ratingLogic;
    }

    public void setRatingLogic(RatingLogic ratingLogic) {
        this.ratingLogic = ratingLogic;
    }

    public UserLogic getUserLogic() {
        return userLogic;
    }

    public void setUserLogic(UserLogic userLogic) {
        this.userLogic = userLogic;
    }
}
