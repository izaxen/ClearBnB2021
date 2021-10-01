package dtos;

import entityDO.Listing;

public class AddAddressDTO {

    private String addressListing;
    private String city;
    private Listing listing;

    public AddAddressDTO (String city, String addressListing) {
        this.addressListing = addressListing;
        this.city = city;
    }

    public AddAddressDTO(String addressListing, String city, Listing listing) {
        this.addressListing = addressListing;
        this.city = city;
        this.listing = listing;
    }

    public AddAddressDTO() {
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
