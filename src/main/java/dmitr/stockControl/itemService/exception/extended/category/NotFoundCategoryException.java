package dmitr.stockControl.itemService.exception.extended.category;

import dmitr.stockControl.itemService.exception.base.NotFoundException;

public class NotFoundCategoryException extends NotFoundException {

    public NotFoundCategoryException() {
        super("category.notFound");
    }
}
