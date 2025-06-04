package dmitr.stockControl.itemService.model.product;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class ProductPageViewDto {

    private UUID id;
    private String name;
    private Float price;
    private List<String> images;
}
