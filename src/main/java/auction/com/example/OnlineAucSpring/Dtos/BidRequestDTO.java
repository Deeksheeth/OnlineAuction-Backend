package auction.com.example.OnlineAucSpring.Dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BidRequestDTO {
    private Long bId;
    private double amount;
    private LocalDateTime timeStamp;
    private Long auctionId;
    private Long bidderId;

}
