package auction.com.example.OnlineAucSpring.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private Long id;
    private double amountPaid;
    private String paymentMode;
    private String status; //Pending, Success, Failed
    private LocalDateTime paymentTime;
    private Long userId;
    private Long auctionId;
}
