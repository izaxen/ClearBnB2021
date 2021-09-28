package dtos;

import entityDO.Listing;

public class UpdateAddressDTO {
    private int id;
    private String address;
    private String city;
    private Listing listing;

    public UpdateAddressDTO() {
    }

    public UpdateAddressDTO(int id, String address, String city, Listing listing) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.listing = listing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
