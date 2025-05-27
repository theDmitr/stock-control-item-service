package dmitr.stockControl.itemService.model.productFeature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFeatureViewDto {

    private String featureName;
    private String featureValue;
}
