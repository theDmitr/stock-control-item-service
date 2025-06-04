package dmitr.stockControl.itemService.model.category;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryPageViewDto {

    private UUID id;
    private String name;
    private Long productsCount;
    private String image;
    private Boolean hasChild;

    //чисто для запроса
    private UUID parentCategoryId;

    public CategoryPageViewDto(String id, String name, Long productsCount, String image, Long childCategoriesCount,
                               String parentCategoryId) {
        this.id = UUID.fromString(id);
        this.name = name;
        this.productsCount = productsCount;
        this.image = image;
        this.hasChild = childCategoriesCount > 1;
        this.parentCategoryId = parentCategoryId != null ? UUID.fromString(parentCategoryId) : null;
    }
}
