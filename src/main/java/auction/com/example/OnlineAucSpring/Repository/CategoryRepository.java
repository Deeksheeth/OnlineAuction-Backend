package auction.com.example.OnlineAucSpring.Repository;

import auction.com.example.OnlineAucSpring.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByname(String name);
}
