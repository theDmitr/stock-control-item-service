package dmitr.stockControl.itemService.helper.mapper.productFeature;

import dmitr.stockControl.itemService.dao.entity.productFeature.ProductFeature;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureCreateDto;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductFeatureMapper {

    ProductFeatureDto toDto(ProductFeature productFeature);
    List<ProductFeatureDto> toDto(List<ProductFeature> productFeatures);
    ProductFeature fromDto(ProductFeatureCreateDto productDto);
}
