package dmitr.stockControl.itemService.helper.mapper.maker;

import dmitr.stockControl.itemService.dao.entity.maker.Maker;
import dmitr.stockControl.itemService.model.maker.MakerCreateDto;
import dmitr.stockControl.itemService.model.maker.MakerDto;
import dmitr.stockControl.itemService.model.maker.MakerUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MakerMapper {

    MakerDto toDto(Maker maker);
    List<MakerDto> toDto(List<Maker> makers);
    Maker fromDto(MakerCreateDto makerDto);
    Maker fromDto(MakerUpdateDto makerDto);
}
