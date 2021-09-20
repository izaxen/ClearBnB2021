package models;

import jakarta.persistence.JoinColumn;

import javax.persistence.ManyToOne;

public class Listing {

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;
    public Listing(){

    }
}
