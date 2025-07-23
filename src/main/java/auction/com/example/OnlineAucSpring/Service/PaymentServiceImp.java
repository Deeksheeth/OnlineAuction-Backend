package auction.com.example.OnlineAucSpring.Service;

import auction.com.example.OnlineAucSpring.Dtos.PaymentRequestDTO;
import auction.com.example.OnlineAucSpring.Dtos.PaymentResponseDto;
import auction.com.example.OnlineAucSpring.Model.Auction;
import auction.com.example.OnlineAucSpring.Model.Payment;
import auction.com.example.OnlineAucSpring.Model.User;
import auction.com.example.OnlineAucSpring.Repository.AuctionRepository;
import auction.com.example.OnlineAucSpring.Repository.PaymentRepository;
import auction.com.example.OnlineAucSpring.Repository.UserRepo;
import auction.com.example.OnlineAucSpring.exception.IllegalMethodException;
import auction.com.example.OnlineAucSpring.exception.ResponseNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImp implements PaymentService{
    private PaymentRepository paymentRepository;
    private UserRepo userRepo;
    private AuctionRepository auctionRepository;
    private ModelMapper modelMapper;

    @Override
    public PaymentRequestDTO processPayment(PaymentRequestDTO requestDTO) {
        User user = userRepo.findById(requestDTO.getUserId())
                .orElseThrow(()->new ResponseNotFoundException("User","id", requestDTO.getUserId()));
        Auction auction = auctionRepository.findById(requestDTO.getAuctionId())
                .orElseThrow(() -> new ResponseNotFoundException("Auction", "id", requestDTO.getAuctionId()));

        if (!auction.isActive() || LocalDateTime.now().isBefore(auction.getEndTime())) {
            throw new IllegalMethodException("Auction is not eligible for payment", 403, requestDTO.getAmountPaid());
        }

        Payment payment = new Payment();
        payment.setAmountPaid(requestDTO.getAmountPaid());
        payment.setPaymentMode(requestDTO.getPaymentMode());
        payment.setPaymentTime(LocalDateTime.now());
        payment.setUser(user);
        payment.setAuction(auction);

        Payment saved = paymentRepository.save(payment);
        return modelMapper.map(saved, PaymentRequestDTO.class);
    }
}
