package auction.com.example.OnlineAucSpring.Service;

import auction.com.example.OnlineAucSpring.Dtos.BidRequestDTO;
import auction.com.example.OnlineAucSpring.Dtos.BidResponseDTO;
import auction.com.example.OnlineAucSpring.Model.Auction;
import auction.com.example.OnlineAucSpring.Model.Bid;
import auction.com.example.OnlineAucSpring.Model.User;
import auction.com.example.OnlineAucSpring.Repository.AuctionRepository;
import auction.com.example.OnlineAucSpring.Repository.BidRepository;
import auction.com.example.OnlineAucSpring.Repository.UserRepo;
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
        User bidder = userRepo.findById(request.getBidderId())
                .orElseThrow(() -> new ResponseNotFoundException("User", "id", request.getBidderId()));

        Bid bid = modelMapper.map(request,Bid.class);
        bid.setAmount(request.getAmount());
        bid.setTimeStamp(LocalDateTime.now());
        bid.setAuction(auction);
        bid.setBidder(bidder);

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
