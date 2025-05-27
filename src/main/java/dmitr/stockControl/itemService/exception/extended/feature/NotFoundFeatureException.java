package dmitr.stockControl.itemService.exception.extended.feature;

import dmitr.stockControl.itemService.exception.base.NotFoundException;

public class NotFoundFeatureException extends NotFoundException {

    public NotFoundFeatureException() {
        super("feature.notFound");
    }
}
