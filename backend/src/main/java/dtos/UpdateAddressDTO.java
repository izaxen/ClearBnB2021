package dtos;

import entityDO.Listing;

public class UpdateAddressDTO {
    private int id;
    private String addressListing;
    private String city;
    private Listing listing;

    public UpdateAddressDTO() {
    }

    public UpdateAddressDTO(int id,String city, String addressListing, Listing listing) {
        this.id = id;
        this.addressListing = addressListing;
        this.city = city;
        this.listing = listing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressListing() {
        return addressListing;
    }

    public void setAddressListing(String addressListing) {
        this.addressListing = addressListing;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

}
