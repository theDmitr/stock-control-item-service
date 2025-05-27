package dmitr.stockControl.itemService.controller.productFeature;

import dmitr.stockControl.itemService.dao.entity.productFeature.ProductFeatureId;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureCreateDto;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureDto;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureViewDto;
import dmitr.stockControl.itemService.service.face.productFeature.ProductFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product_features")
@RequiredArgsConstructor
public class ProductFeatureRestController {

    private final ProductFeatureService productFeatureService;

    @GetMapping("/product/{productId}")
    public List<ProductFeatureDto> getProductFeatures(@PathVariable UUID productId) {
        return productFeatureService.getProductFeaturesByProductId(productId);
    }

    @GetMapping("/product/{productId}/view")
    public List<ProductFeatureViewDto> getProductFeaturesToView(@PathVariable UUID productId) {
        return productFeatureService.getProductFeaturesToView(productId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ProductFeatureDto createProductFeature(@RequestBody ProductFeatureCreateDto productFeatureDto) {
        return productFeatureService.createProductFeature(productFeatureDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/product/{productId}/feature/{featureId}")
    public void deleteProductFeature(@PathVariable UUID productId,
                                     @PathVariable UUID featureId) {
        productFeatureService.deleteProductFeature(new ProductFeatureId(productId, featureId));
    }
}
