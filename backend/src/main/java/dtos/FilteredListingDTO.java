package dtos;

public class FilteredListingDTO {

    private String description;
    private String city;
    private String address;
    private int price;
    private int listingID;

    public FilteredListingDTO() {
    }

    public FilteredListingDTO(int ID, String description, int price, String city, String address) {
        this.description = description;
        this.city = city;
        this.address = address;
        this.price = price;
        this.listingID = ID;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getID() {
        return listingID;
    }

    public void setID(int ID) {
        this.listingID = ID;
    }


    @Override
    public String toString() {
        return "FilteredListingDTO{" +
                "description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", ID=" + listingID +
                '}';
    }
}
