package auction.com.example.OnlineAucSpring.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aucID;

    private String name;
    private String description;
    private double basePrice;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double currentPrice;

    private boolean isActive;
    private Long winnerUserId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
