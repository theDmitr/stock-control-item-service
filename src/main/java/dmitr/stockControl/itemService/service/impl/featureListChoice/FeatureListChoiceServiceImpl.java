package dmitr.stockControl.itemService.service.impl.featureListChoice;

import dmitr.stockControl.itemService.dao.entity.featureListChoice.FeatureListChoice;
import dmitr.stockControl.itemService.dao.repository.feature.FeatureRepository;
import dmitr.stockControl.itemService.dao.repository.featureListChoice.FeatureListChoiceRepository;
import dmitr.stockControl.itemService.exception.extended.ValidationException;
import dmitr.stockControl.itemService.exception.extended.feature.NotFoundFeatureException;
import dmitr.stockControl.itemService.exception.extended.featureListChoice.NotFoundFeatureListChoiceException;
import dmitr.stockControl.itemService.helper.mapper.featureListChoice.FeatureListChoiceMapper;
import dmitr.stockControl.itemService.model.featureListChoice.FeatureListChoiceCreateDto;
import dmitr.stockControl.itemService.model.featureListChoice.FeatureListChoiceDto;
import dmitr.stockControl.itemService.model.featureListChoice.FeatureListChoiceUpdateDto;
import dmitr.stockControl.itemService.model.featureListChoice.face.FeatureListChoiceBaseValidation;
import dmitr.stockControl.itemService.service.face.featureListChoice.FeatureListChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static io.micrometer.common.util.StringUtils.isBlank;

@Service
@RequiredArgsConstructor
public class FeatureListChoiceServiceImpl implements FeatureListChoiceService {

    private final FeatureRepository featureRepository;
    private final FeatureListChoiceRepository featureListChoiceRepository;

    private final FeatureListChoiceMapper featureListChoiceMapper;

    @Override
    public List<FeatureListChoiceDto> getFeatureListChoices() {
        List<FeatureListChoice> featureListChoices = featureListChoiceRepository.findAll();
        return featureListChoiceMapper.toDto(featureListChoices);
    }

    @Override
    public List<FeatureListChoiceDto> getFeatureListChoicesByFeatureId(UUID featureId) {
        List<FeatureListChoice> featureListChoices = featureListChoiceRepository.findByFeatureId(featureId);
        return featureListChoiceMapper.toDto(featureListChoices);
    }

    @Override
    public FeatureListChoiceDto getFeatureListChoice(UUID id) {
        FeatureListChoice featureListChoice = featureListChoiceRepository.findById(id)
                .orElseThrow(NotFoundFeatureListChoiceException::new);
        return featureListChoiceMapper.toDto(featureListChoice);
    }

    @Override
    public FeatureListChoiceDto createFeatureListChoice(FeatureListChoiceCreateDto featureListChoiceDto) {
        creatingValidation(featureListChoiceDto);
        baseValidation(featureListChoiceDto);

        FeatureListChoice featureListChoice = featureListChoiceMapper.fromDto(featureListChoiceDto);
        featureListChoiceRepository.save(featureListChoice);

        return featureListChoiceMapper.toDto(featureListChoice);
    }

    @Override
    public FeatureListChoiceDto updateFeatureListChoice(UUID featureListChoiceId, FeatureListChoiceUpdateDto featureListChoiceDto) {
        FeatureListChoice existsFeatureListChoice = featureListChoiceRepository.findById(featureListChoiceId)
                .orElseThrow(NotFoundFeatureListChoiceException::new);

        baseValidation(featureListChoiceDto);

        FeatureListChoice featureListChoice = featureListChoiceMapper.fromDto(featureListChoiceDto);
        featureListChoice.setId(existsFeatureListChoice.getId());
        featureListChoice.setFeatureId(existsFeatureListChoice.getFeatureId());
        featureListChoiceRepository.save(featureListChoice);

        return featureListChoiceMapper.toDto(featureListChoice);
    }

    private void creatingValidation(FeatureListChoiceCreateDto featureListChoiceDto) {
        UUID featureId = featureListChoiceDto.getFeatureId();
        if (featureId == null) {
            throw new ValidationException("featureListChoice.validation.feature.required");
        }
        featureRepository.findById(featureId)
                .orElseThrow(NotFoundFeatureException::new);
    }

    private void baseValidation(FeatureListChoiceBaseValidation featureListChoice) {
        String value = featureListChoice.getValue();
        if (isBlank(value)) {
            throw new ValidationException("featureListChoice.validation.name.required");
        }
    }

    @Override
    public void deleteFeatureListChoice(UUID id) {
        featureListChoiceRepository.deleteById(id);
    }
}
