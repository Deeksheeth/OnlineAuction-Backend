package auction.com.example.OnlineAucSpring.Controller;

import auction.com.example.OnlineAucSpring.Dtos.PaymentRequestDTO;
import auction.com.example.OnlineAucSpring.Service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private PaymentService paymentService;

    @PostMapping("api/payment/pay")
    public ResponseEntity<PaymentRequestDTO> makePayment(@RequestBody PaymentRequestDTO dto) {
        return new ResponseEntity<>(paymentService.processPayment(dto), HttpStatus.CREATED);
    }
}
