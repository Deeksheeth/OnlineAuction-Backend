package auction.com.example.OnlineAucSpring.Controller;

import auction.com.example.OnlineAucSpring.Dtos.BidRequestDTO;
import auction.com.example.OnlineAucSpring.Dtos.BidResponseDTO;
import auction.com.example.OnlineAucSpring.Service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/bids")
public class BidController {
    @Autowired
    private BidService bidService;

    @PostMapping("/place")
    public ResponseEntity<BidResponseDTO> placingBid(@RequestBody BidRequestDTO requestDTO){
        BidResponseDTO bidResponseDTO = bidService.placeBid(requestDTO);
        return new ResponseEntity<>(bidResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("view/{auctionId}")
    public ResponseEntity<BidResponseDTO> viewBids(@PathVariable Long auctionId){
        BidResponseDTO bidResponseDTO = bidService.viewBidsForAuction(auctionId);
        return new ResponseEntity<>(bidResponseDTO,HttpStatus.OK);
    }

}
