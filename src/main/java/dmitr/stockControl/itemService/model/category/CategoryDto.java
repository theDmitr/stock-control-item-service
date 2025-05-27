package dmitr.stockControl.itemService.model.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private UUID id;
    private String name;
    private String description;
    private UUID parentCategoryId;
}
