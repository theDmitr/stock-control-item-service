package dmitr.stockControl.itemService.model.productFeature;

import dmitr.stockControl.itemService.dao.entity.productFeature.ProductFeatureId;
import dmitr.stockControl.itemService.model.productFeature.face.ProductFeatureBaseValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductFeatureCreateDto implements ProductFeatureBaseValidation {

    private ProductFeatureId id;
    private UUID featureListChoiceId;
}
