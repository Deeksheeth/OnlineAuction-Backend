package auction.com.example.OnlineAucSpring.Service;

import auction.com.example.OnlineAucSpring.Dtos.CategoryRequest;
import auction.com.example.OnlineAucSpring.Dtos.CategoryResponse;

public interface CategoryService {
    CategoryRequest createCategory(CategoryRequest category);
    CategoryResponse getAllCategories();
}
