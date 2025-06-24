package auction.com.example.OnlineAucSpring.Service;

import auction.com.example.OnlineAucSpring.Dtos.CategoryRequest;
import auction.com.example.OnlineAucSpring.Dtos.CategoryResponse;
import auction.com.example.OnlineAucSpring.Model.Category;
import auction.com.example.OnlineAucSpring.Repository.CategoryRepository;
import auction.com.example.OnlineAucSpring.Repository.UserRepo;
import auction.com.example.OnlineAucSpring.exception.APIException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public CategoryRequest createCategory(CategoryRequest categoryRequest) {
        Category category1 = modelMapper.map(categoryRequest,Category.class);
        Category categoryFromDB = categoryRepository.findByname(category1.getName());
        if(categoryFromDB != null)
            throw new APIException("Category with this name "+category1.getName()+" already exists");
        Category save = categoryRepository.save(category1);
        return modelMapper.map(save,CategoryRequest.class);
    }

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryRequest> categoryRequests = categories.stream()
                .map(category -> modelMapper.map(category,CategoryRequest.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryRequests);
        return categoryResponse;
    }
}
