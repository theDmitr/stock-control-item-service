package dmitr.stockControl.itemService.model.feature;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FeatureDto {

    private UUID id;
    private String name;
    private String description;
}
