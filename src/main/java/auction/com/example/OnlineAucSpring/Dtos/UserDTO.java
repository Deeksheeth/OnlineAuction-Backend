package auction.com.example.OnlineAucSpring.Dtos;

import jakarta.persistence.Table;
import lombok.*;

@Data
@Table(name = "`user`")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
}
