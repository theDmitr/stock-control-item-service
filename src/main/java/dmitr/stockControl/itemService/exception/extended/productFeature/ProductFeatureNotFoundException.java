package dmitr.stockControl.itemService.exception.extended.productFeature;

import dmitr.stockControl.itemService.exception.base.NotFoundException;

public class ProductFeatureNotFoundException extends NotFoundException {

    public ProductFeatureNotFoundException() {
        super("productFeature.notFound");
    }
}
