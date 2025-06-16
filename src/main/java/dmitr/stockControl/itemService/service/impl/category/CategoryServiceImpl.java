package dmitr.stockControl.itemService.service.impl.category;

import dmitr.stockControl.itemService.dao.entity.category.Category;
import dmitr.stockControl.itemService.dao.repository.category.CategoryRepository;
import dmitr.stockControl.itemService.exception.extended.ValidationException;
import dmitr.stockControl.itemService.exception.extended.category.NotFoundCategoryException;
import dmitr.stockControl.itemService.helper.mapper.category.CategoryMapper;
import dmitr.stockControl.itemService.model.category.*;
import dmitr.stockControl.itemService.model.category.face.CategoryBaseValidation;
import dmitr.stockControl.itemService.service.face.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static dmitr.stockControl.itemService.utils.Constants.EMPTY_IMAGE_URL;
import static io.micrometer.common.util.StringUtils.isBlank;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDto(categories);
    }

    @Override
    public List<CategoryPageViewDto> getCategoriesToPage(CategoryPageViewFilterDto filter) {
        List<CategoryPageViewDto> categories = categoryRepository.findAllToPageView();
        return categories
                .stream()
                .filter(c -> {
                    if (filter.getParentCategoryId() != null) {
                        return filter.getParentCategoryId().equals(c.getParentCategoryId());
                    }
                    return c.getParentCategoryId() == null;
                })
                .peek(c -> {
                    if (c.getImage() == null) {
                        c.setImage(EMPTY_IMAGE_URL);
                    }
                    boolean hasChild = categories.stream()
                                    .anyMatch(cc -> cc.getParentCategoryId() != null &&
                                            cc.getParentCategoryId().equals(c.getId()));
                    c.setHasChild(hasChild);
                })
                .toList();
    }

    @Override
    public CategoryDto getCategory(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(NotFoundCategoryException::new);
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto createCategory(CategoryCreateDto categoryDto) {
        baseValidation(categoryDto, null);

        Category category = categoryMapper.fromDto(categoryDto);
        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto updateCategory(UUID categoryId, CategoryUpdateDto categoryDto) {
        categoryRepository.findById(categoryId)
                .orElseThrow(NotFoundCategoryException::new);

        baseValidation(categoryDto, categoryId);

        Category category = categoryMapper.fromDto(categoryDto);
        category.setId(categoryId);
        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    private void baseValidation(CategoryBaseValidation category, UUID selfCategoryId) {
        String name = category.getName();
        if (isBlank(name)) {
            throw new ValidationException("category.validation.name.required");
        }

        UUID parentCategoryId = category.getParentCategoryId();
        if (parentCategoryId != null) {
            if (Objects.equals(parentCategoryId, selfCategoryId)) {
                throw new ValidationException("category.validation.parent.selfParent");
            }
            categoryRepository.findById(parentCategoryId)
                    .orElseThrow(() -> new ValidationException("category.validation.parent.notFound"));
        }
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
