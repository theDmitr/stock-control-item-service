package dmitr.stockControl.itemService.model.product;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductsToPageFilterDto {

    private String searchName;
    private UUID categoryId;
}
