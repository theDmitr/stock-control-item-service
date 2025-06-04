package dmitr.stockControl.itemService.model.category;

import dmitr.stockControl.itemService.model.category.face.CategoryBaseValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCreateDto implements CategoryBaseValidation {

    private String name;
    private UUID parentCategoryId;
}
