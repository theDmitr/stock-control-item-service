package dmitr.stockControl.itemService.exception.extended.featureListChoice;

import dmitr.stockControl.itemService.exception.base.NotFoundException;

public class NotFoundFeatureListChoiceException extends NotFoundException {

    public NotFoundFeatureListChoiceException() {
        super("featureListChoice.notFound");
    }
}
