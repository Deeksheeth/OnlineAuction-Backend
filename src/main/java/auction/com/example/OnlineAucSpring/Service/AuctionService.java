package auction.com.example.OnlineAucSpring.Service;

import auction.com.example.OnlineAucSpring.Dtos.AuctionRequest;
import auction.com.example.OnlineAucSpring.Dtos.AuctionResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface AuctionService {
        AuctionRequest createAuction(Long categoryId,AuctionRequest auctionRequest,String token);
        AuctionResponse viewAuction();
        AuctionRequest updateAuctionDetails(Long aucID,AuctionRequest auctionRequest);
        AuctionRequest deleteAuction(Long aucID);
        AuctionResponse getAuctionByCategory(Long categoryId);
        AuctionResponse getAuctionByID(Long id);
}
