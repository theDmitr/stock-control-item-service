package dmitr.stockControl.itemService.model.feature;

import dmitr.stockControl.itemService.model.feature.face.FeatureBaseValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeatureCreateDto implements FeatureBaseValidation {

    private String name;
    private String description;
}
