package dmitr.stockControl.itemService.model.feature;

import dmitr.stockControl.itemService.model.feature.face.FeatureBaseValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeatureUpdateDto implements FeatureBaseValidation {

    private String name;
}
