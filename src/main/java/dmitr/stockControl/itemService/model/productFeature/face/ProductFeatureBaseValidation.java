package dmitr.stockControl.itemService.model.productFeature.face;

import dmitr.stockControl.itemService.dao.entity.productFeature.ProductFeatureId;

import java.util.UUID;

public interface ProductFeatureBaseValidation {

    ProductFeatureId getId();
    UUID getFeatureListChoiceId();
}
