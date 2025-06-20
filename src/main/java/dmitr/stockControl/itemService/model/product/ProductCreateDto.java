package dmitr.stockControl.itemService.model.product;

import dmitr.stockControl.itemService.model.product.face.ProductBaseValidation;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductCreateDto implements ProductBaseValidation {

    private String name;
    private String description;
    private UUID categoryId;
    private UUID makerId;
    private Float price;
}
