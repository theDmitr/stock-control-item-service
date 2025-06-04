package dmitr.stockControl.itemService.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private UUID id;
    private String name;
    private String description;
    private UUID categoryId;
    private UUID makerId;
    private Float price;
}
