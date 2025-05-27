package dmitr.stockControl.itemService.model.product.face;

import java.util.UUID;

public interface ProductBaseValidation {

    String getName();
    String getDescription();
    UUID getCategoryId();
    UUID getMakerId();
}
