package dmitr.stockControl.itemService.service.face.feature;

import dmitr.stockControl.itemService.model.feature.FeatureCreateDto;
import dmitr.stockControl.itemService.model.feature.FeatureDto;
import dmitr.stockControl.itemService.model.feature.FeatureUpdateDto;

import java.util.List;
import java.util.UUID;

public interface FeatureService {

    List<FeatureDto> getFeatures();
    FeatureDto getFeature(UUID id);
    FeatureDto createFeature(FeatureCreateDto featureDto);
    FeatureDto updateFeature(UUID featureId, FeatureUpdateDto featureDto);
    void deleteFeature(UUID id);
}
