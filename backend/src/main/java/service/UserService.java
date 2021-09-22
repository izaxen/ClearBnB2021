package service;

import application.Repositories;
import dtos.LoginUserDTO;
import entityDO.User;
import utils.HashPassword;
import java.util.Optional;

public class UserService {

    Repositories repositories;
    LoginUserDTO loginUserDTO;

    public User convertLoginUserToUser(LoginUserDTO userDTO){
        return new User(userDTO.getEmail(), userDTO.getPw());
    }

    public User loginUser(User user){
        repositories = new Repositories();

        //user =
        Optional<User> userInDB = repositories.getUserRep().findByEmail(user.getEmail());
        if(userInDB.isEmpty()){
            return null;
        }

        if(HashPassword.match(user.getPw(), userInDB.get().getPw())) {
            return userInDB.get();
        }
        else{
            return null;
        }
    }
}
