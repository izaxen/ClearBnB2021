package dtos;

public class RegisterUserDTO {
    private String firstName;
    private String surName;
    private String email;
    private String pw;


    public RegisterUserDTO(String firstName, String surName, String email, String pw) {
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.pw = pw;
    }

    public RegisterUserDTO(){

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

    public String getPw() {
        return pw;
    }
}
