package dmitr.stockControl.itemService.model.product;

import dmitr.stockControl.itemService.model.category.CategoryShortDto;
import dmitr.stockControl.itemService.model.maker.MakerShortDto;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureViewDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class ProductInfoDto {

    private UUID id;
    private String name;
    private String description;
    private CategoryShortDto category;
    private MakerShortDto maker;
    private int rating;
    private Float price;
    private List<String> images;
    private List<ProductFeatureViewDto> features;
}
