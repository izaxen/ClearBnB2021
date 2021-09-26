package service;

import application.Repositories;
import dtos.LoginUserDTO;
import dtos.NoPwUserDTO;
import dtos.RegisterUserDTO;
import entityDO.User;

import java.util.Map;


public class UserService {   // Serverar Application med ett nytt userobjekt med rätt info Klassen blir som en validering.

    // Service turns DTO into a fake object
    public User convertLoginUserToUser(LoginUserDTO userDTO){
        return new User(userDTO.getEmail(), userDTO.getPw());
    }

    public NoPwUserDTO noPwUser (User user) {
        if (user != null) {
            return new NoPwUserDTO(user.getID(), user.getFirstName(), user.getSurName(), user.getEmail());
        }
        return null;
    }

    public User convertRegisterUser(RegisterUserDTO userDTO){
        return new User(userDTO.getFirstName(), userDTO.getSurName(), userDTO.getEmail(), userDTO.getPw());
    }
}
