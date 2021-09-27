package dtos;

import entityDO.Listing;

public class AddAddressDTO {

    private String address;
    private String city;
    private Listing listing;

    public AddAddressDTO (String city, String address) {
        this.address = address;
        this.city = city;
    }

    public AddAddressDTO(String address, String city, Listing listing) {
        this.address = address;
        this.city = city;
        this.listing = listing;
    }

    public AddAddressDTO() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
