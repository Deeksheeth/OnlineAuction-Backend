package auction.com.example.OnlineAucSpring.Controller;

import auction.com.example.OnlineAucSpring.Dtos.AuctionRequest;
import auction.com.example.OnlineAucSpring.Dtos.AuctionResponse;
import auction.com.example.OnlineAucSpring.Service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;

    @PostMapping("admin/categories/{categoryID}/auction")
    public ResponseEntity<AuctionRequest> createAuctions(@RequestHeader("Authorization") String token,
            @RequestBody AuctionRequest auctionRequest, @PathVariable Long categoryID) {
        token = token.substring(7);

        AuctionRequest savedAuction = auctionService.createAuction(categoryID, auctionRequest, token);
        return new ResponseEntity<>(savedAuction, HttpStatus.CREATED);
    }

    @GetMapping("public/auction")
    public ResponseEntity<AuctionResponse> viewAuctions(){
        AuctionResponse auctionResponse = auctionService.viewAuction();
        return new ResponseEntity<>(auctionResponse,HttpStatus.OK);
    }

    @PutMapping("/admin/auction/{aucId}")
    public ResponseEntity<AuctionRequest> updateProduct(@RequestBody AuctionRequest auctionRequest,@PathVariable Long aucId){
        AuctionRequest auction = auctionService.updateAuctionDetails(aucId,auctionRequest);
        return new ResponseEntity<>(auctionRequest,HttpStatus.OK);
    }

    @DeleteMapping("/admin/auction/{aucId}")
    public ResponseEntity<AuctionRequest> deleteProduct(@PathVariable Long aucId){
        AuctionRequest deletedAuction = auctionService.deleteAuction(aucId);
        return new ResponseEntity<>(deletedAuction,HttpStatus.OK);
    }

    @GetMapping("admin/category/{categoryId}")
    public ResponseEntity<AuctionResponse> getAuctionsByCategory(@PathVariable Long categoryId) {
        AuctionResponse auctions = auctionService.getAuctionByCategory(categoryId);
        return new ResponseEntity<>(auctions,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuctionResponse> getAuctionById(@PathVariable Long id) {
        AuctionResponse auctionResponse =  auctionService.getAuctionByID(id);
        return new ResponseEntity<>(auctionResponse,HttpStatus.OK);
    }
}
