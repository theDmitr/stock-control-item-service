package dmitr.stockControl.itemService.helper.mapper.feature;

import dmitr.stockControl.itemService.dao.entity.feature.Feature;
import dmitr.stockControl.itemService.model.feature.FeatureCreateDto;
import dmitr.stockControl.itemService.model.feature.FeatureDto;
import dmitr.stockControl.itemService.model.feature.FeatureUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeatureMapper {

    FeatureDto toDto(Feature feature);
    List<FeatureDto> toDto(List<Feature> features);
    Feature fromDto(FeatureCreateDto featureDto);
    Feature fromDto(FeatureUpdateDto featureDto);
}
