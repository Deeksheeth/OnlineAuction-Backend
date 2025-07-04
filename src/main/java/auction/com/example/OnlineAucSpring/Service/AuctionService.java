package auction.com.example.OnlineAucSpring.Service;

import auction.com.example.OnlineAucSpring.Dtos.AuctionRequest;
import auction.com.example.OnlineAucSpring.Dtos.AuctionResponse;
import org.springframework.stereotype.Service;


public interface AuctionService {
        AuctionRequest createAuction(Long categoryId,AuctionRequest auctionRequest);
        AuctionResponse viewAuction();
        AuctionRequest updateAuctionDetails(Long aucID,AuctionRequest auctionRequest);
        AuctionRequest deleteAuction(Long aucID);
}
