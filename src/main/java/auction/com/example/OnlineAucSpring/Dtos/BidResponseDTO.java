package auction.com.example.OnlineAucSpring.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidResponseDTO {
    List<BidRequestDTO> details ;
}
