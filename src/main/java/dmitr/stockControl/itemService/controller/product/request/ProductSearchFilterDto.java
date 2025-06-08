package dmitr.stockControl.itemService.controller.product.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class ProductSearchFilterDto {

    private List<UUID> productIds;
}
