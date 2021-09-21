package application.user;

import models.User;
import repositories.UserRepository;
import utils.HashPassword;

import java.util.Optional;


public class UserAccess {

    UserRepository userRepository;

    public UserAccess(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createNewUser(User user) {

        if (userRepository.emailNotExist(user.getEmail())) {
            String hashedPassword = HashPassword.hash(user.getPw());
            user.setPw(hashedPassword);
            return userRepository.save(user);
        }
        return null;
    }

     public User loginUser(User user){
         Optional<User> userInDB = userRepository.findByEmail(user.getEmail());
         if(!userInDB.isPresent()){
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
