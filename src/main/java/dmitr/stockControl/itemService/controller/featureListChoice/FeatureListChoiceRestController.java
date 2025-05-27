package dmitr.stockControl.itemService.controller.featureListChoice;

import dmitr.stockControl.itemService.model.featureListChoice.FeatureListChoiceCreateDto;
import dmitr.stockControl.itemService.model.featureListChoice.FeatureListChoiceDto;
import dmitr.stockControl.itemService.model.featureListChoice.FeatureListChoiceUpdateDto;
import dmitr.stockControl.itemService.service.face.featureListChoice.FeatureListChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/featureListChoices")
@RequiredArgsConstructor
public class FeatureListChoiceRestController {

    private final FeatureListChoiceService featureListChoiceService;

    @GetMapping
    public List<FeatureListChoiceDto> getFeatureListChoices() {
        return featureListChoiceService.getFeatureListChoices();
    }

    @GetMapping("/{featureListChoiceId}")
    public FeatureListChoiceDto getFeatureListChoice(@PathVariable UUID featureListChoiceId) {
        return featureListChoiceService.getFeatureListChoice(featureListChoiceId);
    }

    @GetMapping("/feature/{featureId}")
    public List<FeatureListChoiceDto> getFeatureListChoicesByFeatureId(@PathVariable UUID featureId) {
        return featureListChoiceService.getFeatureListChoicesByFeatureId(featureId);
    }

    @PostMapping
    public FeatureListChoiceDto createFeatureListChoice(@RequestBody FeatureListChoiceCreateDto featureListChoiceDto) {
        return featureListChoiceService.createFeatureListChoice(featureListChoiceDto);
    }

    @PatchMapping("/{featureListChoiceId}")
    public FeatureListChoiceDto updateFeatureListChoice(@PathVariable UUID featureListChoiceId,
                                              @RequestBody FeatureListChoiceUpdateDto featureListChoiceDto) {
        return featureListChoiceService.updateFeatureListChoice(featureListChoiceId, featureListChoiceDto);
    }

    @DeleteMapping("/{featureListChoiceId}")
    public void deleteFeatureListChoice(@PathVariable UUID featureListChoiceId) {
        featureListChoiceService.deleteFeatureListChoice(featureListChoiceId);
    }
}
