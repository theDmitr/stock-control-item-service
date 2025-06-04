package dmitr.stockControl.itemService.model.category.face;

import java.util.UUID;

public interface CategoryBaseValidation {

    String getName();
    UUID getParentCategoryId();
}
