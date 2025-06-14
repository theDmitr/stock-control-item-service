package dmitr.stockControl.itemService.service.face.category;

import dmitr.stockControl.itemService.model.category.*;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<CategoryDto> getCategories();
    CategoryDto getCategory(UUID id);
    CategoryDto createCategory(CategoryCreateDto categoryDto);
    CategoryDto updateCategory(UUID categoryId, CategoryUpdateDto categoryDto);
    void deleteCategory(UUID id);
    List<CategoryPageViewDto> getCategoriesToPage(CategoryPageViewFilterDto filter);
}
