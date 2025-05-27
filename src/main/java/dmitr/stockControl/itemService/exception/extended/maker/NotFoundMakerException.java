package dmitr.stockControl.itemService.exception.extended.maker;

import dmitr.stockControl.itemService.exception.base.NotFoundException;

public class NotFoundMakerException extends NotFoundException {

    public NotFoundMakerException() {
        super("maker.notFound");
    }
}
