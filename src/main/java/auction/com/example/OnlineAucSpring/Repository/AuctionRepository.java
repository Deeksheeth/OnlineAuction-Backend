package auction.com.example.OnlineAucSpring.Repository;

import auction.com.example.OnlineAucSpring.Model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction,Long> {
    Auction findByname(String name);
//    List<Auction> findByActiveTrue();
}
