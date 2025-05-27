package dmitr.stockControl.itemService.controller.maker;

import dmitr.stockControl.itemService.model.maker.MakerCreateDto;
import dmitr.stockControl.itemService.model.maker.MakerDto;
import dmitr.stockControl.itemService.model.maker.MakerUpdateDto;
import dmitr.stockControl.itemService.service.face.maker.MakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/makers")
@RequiredArgsConstructor
public class MakerRestController {

    private final MakerService makerService;

    @GetMapping
    public List<MakerDto> getMakers() {
        return makerService.getMakers();
    }

    @GetMapping("/{makerId}")
    public MakerDto getMaker(@PathVariable UUID makerId) {
        return makerService.getMaker(makerId);
    }

    @PostMapping
    public MakerDto createMaker(@RequestBody MakerCreateDto MakerDto) {
        return makerService.createMaker(MakerDto);
    }

    @PatchMapping("/{makerId}")
    public MakerDto updateMaker(@PathVariable UUID makerId,
                                    @RequestBody MakerUpdateDto MakerDto) {
        return makerService.updateMaker(makerId, MakerDto);
    }

    @DeleteMapping("/{makerId}")
    public void deleteMaker(@PathVariable UUID makerId) {
        makerService.deleteMaker(makerId);
    }
}
