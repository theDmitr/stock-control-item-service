package dmitr.stockControl.itemService.model.featureListChoice;

import dmitr.stockControl.itemService.model.featureListChoice.face.FeatureListChoiceBaseValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FeatureListChoiceCreateDto implements FeatureListChoiceBaseValidation {

    private UUID featureId;
    private String value;
}
