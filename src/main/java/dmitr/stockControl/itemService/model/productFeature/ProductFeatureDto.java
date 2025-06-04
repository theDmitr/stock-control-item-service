package dmitr.stockControl.itemService.model.productFeature;

import dmitr.stockControl.itemService.dao.entity.productFeature.ProductFeatureId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductFeatureDto {

    private ProductFeatureId id;
    private String value;
}
