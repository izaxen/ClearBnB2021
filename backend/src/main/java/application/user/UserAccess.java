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

        public User createNewUser(User user){
            String hashedPassword = HashPassword.hash(user.getPw());
            user.setPw(hashedPassword);
            return userRepository.save(user);
        }
}
