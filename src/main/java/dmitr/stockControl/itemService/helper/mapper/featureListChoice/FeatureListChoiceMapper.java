package dmitr.stockControl.itemService.helper.mapper.featureListChoice;

import dmitr.stockControl.itemService.dao.entity.featureListChoice.FeatureListChoice;
import dmitr.stockControl.itemService.model.featureListChoice.FeatureListChoiceCreateDto;
import dmitr.stockControl.itemService.model.featureListChoice.FeatureListChoiceDto;
import dmitr.stockControl.itemService.model.featureListChoice.FeatureListChoiceUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeatureListChoiceMapper {

    FeatureListChoiceDto toDto(FeatureListChoice featureListChoice);
    List<FeatureListChoiceDto> toDto(List<FeatureListChoice> featureListChoices);
    FeatureListChoice fromDto(FeatureListChoiceCreateDto featureListChoiceDto);
    FeatureListChoice fromDto(FeatureListChoiceUpdateDto featureListChoiceDto);
}
