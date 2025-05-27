package dmitr.stockControl.itemService.controller.category;

import dmitr.stockControl.itemService.model.category.CategoryCreateDto;
import dmitr.stockControl.itemService.model.category.CategoryDto;
import dmitr.stockControl.itemService.model.category.CategoryUpdateDto;
import dmitr.stockControl.itemService.service.face.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getCategory(@PathVariable UUID categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryCreateDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{categoryId}")
    public CategoryDto updateCategory(@PathVariable UUID categoryId,
                                    @RequestBody CategoryUpdateDto categoryDto) {
        return categoryService.updateCategory(categoryId, categoryDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable UUID categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
