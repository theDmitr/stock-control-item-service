package dmitr.stockControl.itemService.service.impl.feature;

import dmitr.stockControl.itemService.dao.entity.feature.Feature;
import dmitr.stockControl.itemService.dao.repository.feature.FeatureRepository;
import dmitr.stockControl.itemService.exception.extended.ValidationException;
import dmitr.stockControl.itemService.exception.extended.feature.NotFoundFeatureException;
import dmitr.stockControl.itemService.helper.mapper.feature.FeatureMapper;
import dmitr.stockControl.itemService.model.feature.FeatureCreateDto;
import dmitr.stockControl.itemService.model.feature.FeatureDto;
import dmitr.stockControl.itemService.model.feature.FeatureUpdateDto;
import dmitr.stockControl.itemService.model.feature.face.FeatureBaseValidation;
import dmitr.stockControl.itemService.service.face.feature.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static io.micrometer.common.util.StringUtils.isBlank;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    private final FeatureMapper featureMapper;

    @Override
    public List<FeatureDto> getFeatures() {
        List<Feature> features = featureRepository.findAll();
        return featureMapper.toDto(features);
    }

    @Override
    public FeatureDto getFeature(UUID id) {
        Feature feature = featureRepository.findById(id)
                .orElseThrow(NotFoundFeatureException::new);
        return featureMapper.toDto(feature);
    }

    @Override
    public FeatureDto createFeature(FeatureCreateDto featureDto) {
        baseValidation(featureDto);

        Feature feature = featureMapper.fromDto(featureDto);
        featureRepository.save(feature);

        return featureMapper.toDto(feature);
    }

    @Override
    public FeatureDto updateFeature(UUID featureId, FeatureUpdateDto featureDto) {
        featureRepository.findById(featureId)
                .orElseThrow(NotFoundFeatureException::new);

        baseValidation(featureDto);

        Feature feature = featureMapper.fromDto(featureDto);
        feature.setId(featureId);
        featureRepository.save(feature);

        return featureMapper.toDto(feature);
    }

    private void baseValidation(FeatureBaseValidation feature) {
        String name = feature.getName();
        String description = feature.getDescription();
        if (isBlank(name)) {
            throw new ValidationException("feature.validation.name.required");
        }
        if (isBlank(description)) {
            throw new ValidationException("feature.validation.description.required");
        }
    }

    @Override
    public void deleteFeature(UUID id) {
        featureRepository.deleteById(id); // todo валидация, не используется ли где-то
    }
}
