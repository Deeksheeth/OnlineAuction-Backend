package auction.com.example.OnlineAucSpring.Repository;

import auction.com.example.OnlineAucSpring.Model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid,Long> {
    List<Bid> findByAuction_AucID(Long aucId);
//    List<Bid> findByAuctionIdOrderByBidAmountDesc(Long auctionId);
}
