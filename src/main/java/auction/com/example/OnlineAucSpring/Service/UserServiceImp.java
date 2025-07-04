package auction.com.example.OnlineAucSpring.Service;

import auction.com.example.OnlineAucSpring.Dtos.UserDTO;
import auction.com.example.OnlineAucSpring.Dtos.UserResponse;
import auction.com.example.OnlineAucSpring.Model.User;
import auction.com.example.OnlineAucSpring.Repository.UserRepo;
import auction.com.example.OnlineAucSpring.exception.APIException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO,User.class);
        User userFromDB = userRepo.findByUserName(user.getUserName());
        if(userFromDB != null)
            throw new APIException("User with the name "+user.getUserName()+" already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveUser =userRepo.save(user);
        return modelMapper.map(saveUser, UserDTO.class);
    }

    @Override
    public UserResponse getUser() {
        List<User> user = userRepo.findAll();
        List<UserDTO> userDTOS = user.stream()
                .map( user1 -> modelMapper.map(user1,UserDTO.class))
                .toList();
        UserResponse userResponse = new UserResponse();
        userResponse.setContent(userDTOS);
        return userResponse;
    }
}
