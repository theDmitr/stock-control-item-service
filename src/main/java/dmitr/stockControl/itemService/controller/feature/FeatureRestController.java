package dmitr.stockControl.itemService.controller.feature;

import dmitr.stockControl.itemService.model.feature.FeatureCreateDto;
import dmitr.stockControl.itemService.model.feature.FeatureDto;
import dmitr.stockControl.itemService.model.feature.FeatureUpdateDto;
import dmitr.stockControl.itemService.service.face.feature.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/features")
@RequiredArgsConstructor
public class FeatureRestController {

    private final FeatureService featureService;

    @GetMapping
    public List<FeatureDto> getFeatures() {
        return featureService.getFeatures();
    }

    @GetMapping("/{featureId}")
    public FeatureDto getFeature(@PathVariable UUID featureId) {
        return featureService.getFeature(featureId);
    }

    @PostMapping
    public FeatureDto createFeature(@RequestBody FeatureCreateDto featureDto) {
        return featureService.createFeature(featureDto);
    }

    @PatchMapping("/{featureId}")
    public FeatureDto updateFeature(@PathVariable UUID featureId,
                                    @RequestBody FeatureUpdateDto featureDto) {
        return featureService.updateFeature(featureId, featureDto);
    }

    @DeleteMapping("/{featureId}")
    public void deleteFeature(@PathVariable UUID featureId) {
        featureService.deleteFeature(featureId);
    }
}
