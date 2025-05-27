package dmitr.stockControl.itemService.service.impl.maker;

import dmitr.stockControl.itemService.dao.entity.maker.Maker;
import dmitr.stockControl.itemService.dao.repository.maker.MakerRepository;
import dmitr.stockControl.itemService.exception.extended.ValidationException;
import dmitr.stockControl.itemService.exception.extended.maker.NotFoundMakerException;
import dmitr.stockControl.itemService.helper.mapper.maker.MakerMapper;
import dmitr.stockControl.itemService.model.maker.MakerCreateDto;
import dmitr.stockControl.itemService.model.maker.MakerDto;
import dmitr.stockControl.itemService.model.maker.MakerUpdateDto;
import dmitr.stockControl.itemService.model.maker.face.MakerBaseValidation;
import dmitr.stockControl.itemService.service.face.maker.MakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static io.micrometer.common.util.StringUtils.isBlank;

@Service
@RequiredArgsConstructor
public class MakerServiceImpl implements MakerService {

    private final MakerRepository makerRepository;

    private final MakerMapper makerMapper;

    @Override
    public List<MakerDto> getMakers() {
        List<Maker> makers = makerRepository.findAll();
        return makerMapper.toDto(makers);
    }

    @Override
    public MakerDto getMaker(UUID id) {
        Maker maker = makerRepository.findById(id)
                .orElseThrow(NotFoundMakerException::new);
        return makerMapper.toDto(maker);
    }

    @Override
    public MakerDto createMaker(MakerCreateDto makerDto) {
        baseValidation(makerDto);

        Maker maker = makerMapper.fromDto(makerDto);
        makerRepository.save(maker);

        return makerMapper.toDto(maker);
    }

    @Override
    public MakerDto updateMaker(UUID makerId, MakerUpdateDto makerDto) {
        makerRepository.findById(makerId)
                .orElseThrow(NotFoundMakerException::new);

        baseValidation(makerDto);

        Maker maker = makerMapper.fromDto(makerDto);
        maker.setId(makerId);
        makerRepository.save(maker);

        return makerMapper.toDto(maker);
    }

    private void baseValidation(MakerBaseValidation maker) {
        String name = maker.getName();
        if (isBlank(name)) {
            throw new ValidationException("maker.validation.name.required");
        }
    }

    @Override
    public void deleteMaker(UUID id) {
        makerRepository.deleteById(id);
    }
}
