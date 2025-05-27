package dmitr.stockControl.itemService.model.featureListChoice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FeatureListChoiceDto {

    private UUID id;
    private UUID featureId;
    private String value;
}
