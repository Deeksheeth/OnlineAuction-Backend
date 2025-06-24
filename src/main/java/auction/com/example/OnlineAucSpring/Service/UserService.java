package auction.com.example.OnlineAucSpring.Service;

import auction.com.example.OnlineAucSpring.Dtos.UserDTO;
import auction.com.example.OnlineAucSpring.Dtos.UserResponse;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserResponse getUser();


}
