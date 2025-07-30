package auction.com.example.OnlineAucSpring.Service;

import auction.com.example.OnlineAucSpring.Dtos.AuctionRequest;
import auction.com.example.OnlineAucSpring.Dtos.AuctionResponse;
import auction.com.example.OnlineAucSpring.Model.Auction;
import auction.com.example.OnlineAucSpring.Model.Category;
import auction.com.example.OnlineAucSpring.Model.User;
import auction.com.example.OnlineAucSpring.Repository.AuctionRepository;
import auction.com.example.OnlineAucSpring.Repository.CategoryRepository;
import auction.com.example.OnlineAucSpring.Repository.UserRepo;
import auction.com.example.OnlineAucSpring.config.JwtUtil;
import auction.com.example.OnlineAucSpring.exception.ResponseNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuctionServiceImp implements AuctionService{

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuctionRequest createAuction(Long categoryId, AuctionRequest auctionRequest,String token) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow( ()-> new ResponseNotFoundException("Category","categoryName",categoryId));
        String email = jwtUtil.extractEmail(token);
//        if (auctionRequest.getOwnerID() == null) {
//            throw new IllegalArgumentException("Owner ID cannot be null");
//        }
        User owner = userRepo.findByEmail(email)
                .orElseThrow( ()-> new ResponseNotFoundException("User","id", auctionRequest.getOwnerID()));

        Auction auction = modelMapper.map(auctionRequest,Auction.class);
        auction.setCategory(category);
        auction.setOwner(owner);
        auction.setCurrentPrice(0);
        auction.setActive(true);

        Auction savedAuction = auctionRepository.save(auction);
        return modelMapper.map(savedAuction,AuctionRequest.class);
    }


    @Override
    public AuctionResponse viewAuction() {
        List<Auction> auctions =auctionRepository.findAll();
        List<AuctionRequest> auctionRequests = auctions.stream()
                .map(auction -> modelMapper.map(auction,AuctionRequest.class))
                .toList();
        AuctionResponse auctionResponse = new AuctionResponse();
        auctionResponse.setContent(auctionRequests);
        return auctionResponse;
    }

    @Override
    public AuctionRequest updateAuctionDetails(Long aucID, AuctionRequest auctionRequest) {
        Auction auctionFromDB = auctionRepository.findById(aucID)
                .orElseThrow(()-> new ResponseNotFoundException("Auction","auctionID",aucID));
        User owner = userRepo.findById(auctionRequest.getOwnerID())
                .orElseThrow( ()-> new ResponseNotFoundException("User","id", auctionRequest.getOwnerID()));
        Auction auction = modelMapper.map(auctionRequest,Auction.class);
        auctionFromDB.setName(auction.getName());
        auctionFromDB.setDescription(auction.getDescription());
        auctionFromDB.setBasePrice(auction.getBasePrice());
        auctionFromDB.setLocation(auction.getLocation());
        auctionFromDB.setStartTime(auction.getStartTime());
        auctionFromDB.setEndTime(auction.getEndTime());
        auctionFromDB.setOwner(owner);
        auctionFromDB.setCurrentPrice(auction.getCurrentPrice());
        Auction savedAuction = auctionRepository.save(auctionFromDB);
        return modelMapper.map(savedAuction,AuctionRequest.class);
    }

    @Override
    public AuctionRequest deleteAuction(Long aucID) {
        Auction auctionFromDb = auctionRepository.findById(aucID)
                .orElseThrow(() ->new ResponseNotFoundException("Auction","auctionId",aucID));
        auctionRepository.delete(auctionFromDb);
        return modelMapper.map(auctionFromDb,AuctionRequest.class);
    }

    @Override
    public AuctionResponse getAuctionByCategory(Long categoryId) {
        List<Auction> auctions = auctionRepository.findAll().stream()
                .filter(a-> a.getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
        List<AuctionRequest> auctionRequests = auctions.stream()
                .map(auction -> modelMapper.map(auction,AuctionRequest.class))
                .toList();
        AuctionResponse auctionResponse = new AuctionResponse();
        auctionResponse.setContent(auctionRequests);
        return auctionResponse;
    }

    @Override
    public AuctionResponse getAuctionByID(Long aucId) {
        Auction auctions = auctionRepository.findById(aucId)
                .orElseThrow(()-> new ResponseNotFoundException("Auction","auctionID",aucId));
        List<Auction> auctionList = List.of(auctions);
        List<AuctionRequest> auctionRequest = auctionList.stream()
                .map(a->modelMapper.map(a,AuctionRequest.class))
                .toList();
        AuctionResponse auctionResponse = new AuctionResponse();
        auctionResponse.setContent(auctionRequest);
        return auctionResponse;
    }
}
