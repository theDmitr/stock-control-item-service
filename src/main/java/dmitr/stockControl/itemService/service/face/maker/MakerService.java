package dmitr.stockControl.itemService.service.face.maker;

import dmitr.stockControl.itemService.model.maker.MakerCreateDto;
import dmitr.stockControl.itemService.model.maker.MakerDto;
import dmitr.stockControl.itemService.model.maker.MakerUpdateDto;

import java.util.List;
import java.util.UUID;

public interface MakerService {

    List<MakerDto> getMakers();
    MakerDto getMaker(UUID id);
    MakerDto createMaker(MakerCreateDto makerDto);
    MakerDto updateMaker(UUID makerId, MakerUpdateDto makerDto);
    void deleteMaker(UUID id);
}
