package auction.com.example.OnlineAucSpring.Dtos;

import auction.com.example.OnlineAucSpring.Model.Role;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String userName;
    private String email;
    private String password;
    private Role role;
}
