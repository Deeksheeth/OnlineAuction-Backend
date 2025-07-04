package auction.com.example.OnlineAucSpring.Repository;

import auction.com.example.OnlineAucSpring.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//import java.lang.ScopedValue;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    Optional<User> findByEmail(String email);

//    ThreadLocal<Object> context = new ThreadLocal<>();
//
//    static Object findByEmail(String username) {
//        context.set(username);
//        Object user = UserRepo.findByEmail(username);
//        context.remove();
//        return user;
//    }
}
