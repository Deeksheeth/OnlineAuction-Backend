package auction.com.example.OnlineAucSpring.Service;

import auction.com.example.OnlineAucSpring.Dtos.BidRequestDTO;
import auction.com.example.OnlineAucSpring.Dtos.BidResponseDTO;

import java.util.List;

public interface BidService {
    BidResponseDTO placeBid(BidRequestDTO bidRequestDTO);
    BidResponseDTO viewBidsForAuction(Long aucId);
}
