package dtos;

import entityDO.Listing;

public class AddAddressDTO {
    private String address;
    private String city;

    public AddAddressDTO (String city, String address) {
        this.address = address;
        this.city = city;
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

}
