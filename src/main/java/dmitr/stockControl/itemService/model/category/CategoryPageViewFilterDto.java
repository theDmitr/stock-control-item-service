package dmitr.stockControl.itemService.model.category;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryPageViewFilterDto {

    private UUID parentCategoryId;
}
