package auction.com.example.OnlineAucSpring.Service;

import auction.com.example.OnlineAucSpring.Dtos.PaymentRequestDTO;

public interface PaymentService {
    PaymentRequestDTO processPayment(PaymentRequestDTO requestDTO);
}
