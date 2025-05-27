package dmitr.stockControl.itemService.model.featureListChoice;

import dmitr.stockControl.itemService.model.featureListChoice.face.FeatureListChoiceBaseValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeatureListChoiceUpdateDto implements FeatureListChoiceBaseValidation {

    private String value;
}
