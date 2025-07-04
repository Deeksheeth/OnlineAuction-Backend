package auction.com.example.OnlineAucSpring.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`user`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
