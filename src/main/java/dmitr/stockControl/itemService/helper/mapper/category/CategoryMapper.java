package dmitr.stockControl.itemService.helper.mapper.category;

import dmitr.stockControl.itemService.dao.entity.category.Category;
import dmitr.stockControl.itemService.model.category.CategoryCreateDto;
import dmitr.stockControl.itemService.model.category.CategoryDto;
import dmitr.stockControl.itemService.model.category.CategoryUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);
    List<CategoryDto> toDto(List<Category> categories);
    Category fromDto(CategoryCreateDto categoryDto);
    Category fromDto(CategoryUpdateDto categoryDto);
}
