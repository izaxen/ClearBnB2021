package dtos;

import entityDO.Listing;

public class AddAddressDTO {

    private String addressListing;
    private String city;
    private Listing listing;

    public AddAddressDTO() {
    }

    public String getAddressListing() {
        return addressListing;
    }

    public String getCity() {
        return city;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public void setAddressListing(String addressListing) {
        this.addressListing = addressListing;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
