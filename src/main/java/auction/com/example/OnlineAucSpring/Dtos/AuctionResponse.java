package auction.com.example.OnlineAucSpring.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionResponse {
    List<AuctionRequest> content;
}
