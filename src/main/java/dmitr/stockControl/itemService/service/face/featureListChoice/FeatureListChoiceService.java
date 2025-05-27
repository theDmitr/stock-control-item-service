package dmitr.stockControl.itemService.service.face.featureListChoice;

import dmitr.stockControl.itemService.model.featureListChoice.FeatureListChoiceCreateDto;
import dmitr.stockControl.itemService.model.featureListChoice.FeatureListChoiceDto;
import dmitr.stockControl.itemService.model.featureListChoice.FeatureListChoiceUpdateDto;

import java.util.List;
import java.util.UUID;

public interface FeatureListChoiceService {

    List<FeatureListChoiceDto> getFeatureListChoices();
    List<FeatureListChoiceDto> getFeatureListChoicesByFeatureId(UUID featureId);
    FeatureListChoiceDto getFeatureListChoice(UUID id);
    FeatureListChoiceDto createFeatureListChoice(FeatureListChoiceCreateDto featureListChoiceDto);
    FeatureListChoiceDto updateFeatureListChoice(UUID featureListChoiceId, FeatureListChoiceUpdateDto featureListChoiceDto);
    void deleteFeatureListChoice(UUID id);
}
