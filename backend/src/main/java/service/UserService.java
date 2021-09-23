package service;

import application.Repositories;
import dtos.LoginUserDTO;
import entityDO.User;


public class UserService {   // Serverar Application med ett nytt userobjekt med rätt info Klassen blir som en validering.

    public User convertLoginUserToUser(LoginUserDTO userDTO){
        return new User(userDTO.getEmail(), userDTO.getPw());
    }


}
