package auction.com.example.OnlineAucSpring.Repository;

import auction.com.example.OnlineAucSpring.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
