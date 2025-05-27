package dmitr.stockControl.itemService.exception.extended.product;

import dmitr.stockControl.itemService.exception.base.NotFoundException;

public class NotFoundProductException extends NotFoundException {

    public NotFoundProductException() {
        super("product.notFound");
    }
}
