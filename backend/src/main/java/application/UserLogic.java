package application;

import dtos.LoginUserDTO;
import dtos.RegisterUserDTO;
import entityDO.User;
import mapper.UserMapper;
import repositories.UserRepository;
import utils.HashPassword;

import java.util.Optional;


public class UserLogic {
    UserMapper userMap;
    UserRepository userRepository;

    public UserLogic(UserRepository userRepository) {
        userMap = new UserMapper();
        this.userRepository = userRepository;
    }

    public User createNewUser(RegisterUserDTO dto) {
        User user = userMap.convertRegisterUser(dto);

        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            String hashedPassword = HashPassword.hash(user.getPw());
            user.setPw(hashedPassword);
            return userRepository.save(user);
        }
        return null;
    }

    public User loginUser(LoginUserDTO dto) {
        User user = userMap.convertLoginUserToUser(dto);
        Optional<User> userInDB = userRepository.findByEmail(dto.getEmail());

        if (userInDB.isEmpty()) {
            return null;
        }

        if (HashPassword.match(user.getPw(), userInDB.get().getPw())) {
            return userInDB.get();
        } else {
            return null;
        }
    }
}
