package dmitr.stockControl.itemService.service.face.productFeature;

import dmitr.stockControl.itemService.dao.entity.productFeature.ProductFeatureId;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureCreateDto;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureDto;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureViewDto;

import java.util.List;
import java.util.UUID;

public interface ProductFeatureService {

    List<ProductFeatureDto> getProductFeaturesByProductId(UUID productId);
    List<ProductFeatureViewDto> getProductFeaturesToView(UUID productId);
    ProductFeatureDto createProductFeature(ProductFeatureCreateDto productFeatureDto);
    void deleteProductFeature(ProductFeatureId productFeatureId);
}
