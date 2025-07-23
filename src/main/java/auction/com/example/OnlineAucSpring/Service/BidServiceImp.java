package auction.com.example.OnlineAucSpring.Service;

import auction.com.example.OnlineAucSpring.Dtos.BidRequestDTO;
import auction.com.example.OnlineAucSpring.Dtos.BidResponseDTO;
import auction.com.example.OnlineAucSpring.Model.Auction;
import auction.com.example.OnlineAucSpring.Model.Bid;
import auction.com.example.OnlineAucSpring.Model.User;
import auction.com.example.OnlineAucSpring.Repository.AuctionRepository;
import auction.com.example.OnlineAucSpring.Repository.BidRepository;
import auction.com.example.OnlineAucSpring.Repository.UserRepo;
import auction.com.example.OnlineAucSpring.exception.IllegalMethodException;
import auction.com.example.OnlineAucSpring.exception.ResponseNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BidServiceImp implements BidService{

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BidResponseDTO placeBid(BidRequestDTO request) {
        Auction auction = auctionRepository.findById(request.getAuctionId())
                .orElseThrow(() -> new ResponseNotFoundException("Auction", "id", request.getAuctionId()));

        if(!auction.isActive()){
            throw new IllegalMethodException("Bidding is closed for this auction",400,LocalDateTime.now());
        }

        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(auction.getStartTime()) || now.isAfter(auction.getEndTime())) {
            throw new IllegalMethodException("Auction is not in the given time period",400,LocalDateTime.now());
        }

        if (request.getAmount() <= auction.getCurrentPrice()) {
            throw new IllegalMethodException("Bid should be greater than the current price",400,LocalDateTime.now());
        }

        User bidder = userRepo.findById(request.getBidderId())
                .orElseThrow(() -> new ResponseNotFoundException("User", "id", request.getBidderId()));

        Bid bid = modelMapper.map(request,Bid.class);
        bid.setAmount(request.getAmount());
        bid.setTimeStamp(LocalDateTime.now());
        bid.setAuction(auction);
        bid.setBidder(bidder);

        auction.setCurrentPrice(bid.getAmount());
        auction.setWinnerUserId(bidder.getUserId());
        auctionRepository.save(auction);

        Bid savedBid = bidRepository.save(bid);
        return modelMapper.map(savedBid, BidResponseDTO.class);
    }

    @Override
    public BidResponseDTO viewBidsForAuction(Long aucId) {
        List<Bid> bids = bidRepository.findByAuction_AucID(aucId);
        List<BidRequestDTO> bidRequests = bids.stream()
                .map(bid -> modelMapper.map(bid,BidRequestDTO.class))
                .toList();
        BidResponseDTO bidResponseDTO = new BidResponseDTO();
        bidResponseDTO.setDetails(bidRequests);
        return bidResponseDTO;
    }
}
