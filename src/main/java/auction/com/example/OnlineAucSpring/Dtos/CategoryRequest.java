package auction.com.example.OnlineAucSpring.Dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    private Long id;
    private String name;
    private String description;
}
