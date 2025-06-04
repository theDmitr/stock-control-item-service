package dmitr.stockControl.itemService.model.productFeature;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductFeatureViewDto {

    private String featureName;
    private String featureValue;

    public ProductFeatureViewDto(String featureName, String featureValue) {
        this.featureName = featureName;
        this.featureValue = featureValue;
    }
}
