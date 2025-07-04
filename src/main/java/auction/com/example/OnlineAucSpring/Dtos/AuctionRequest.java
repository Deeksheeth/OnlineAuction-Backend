package auction.com.example.OnlineAucSpring.Dtos;

import lombok.*;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Getter
//@Setter
public class AuctionRequest {

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


    private Long ownerID;
}
