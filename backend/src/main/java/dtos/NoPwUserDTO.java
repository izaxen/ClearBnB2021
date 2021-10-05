package dtos;

public class NoPwUserDTO {
    private int id;
    private String firstName;
    private String surName;
    private String email;


    public NoPwUserDTO(int id, String firstName, String surName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
    }

    public NoPwUserDTO(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
