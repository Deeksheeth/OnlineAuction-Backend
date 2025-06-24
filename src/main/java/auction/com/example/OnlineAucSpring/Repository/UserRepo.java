package auction.com.example.OnlineAucSpring.Repository;

import auction.com.example.OnlineAucSpring.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
