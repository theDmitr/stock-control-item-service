package dmitr.stockControl.itemService.service.impl.productFeature;

import dmitr.stockControl.itemService.dao.entity.feature.Feature;
import dmitr.stockControl.itemService.dao.entity.featureListChoice.FeatureListChoice;
import dmitr.stockControl.itemService.dao.entity.productFeature.ProductFeature;
import dmitr.stockControl.itemService.dao.entity.productFeature.ProductFeatureId;
import dmitr.stockControl.itemService.dao.repository.feature.FeatureRepository;
import dmitr.stockControl.itemService.dao.repository.featureListChoice.FeatureListChoiceRepository;
import dmitr.stockControl.itemService.dao.repository.product.ProductRepository;
import dmitr.stockControl.itemService.dao.repository.productFeature.ProductFeatureRepository;
import dmitr.stockControl.itemService.exception.extended.ValidationException;
import dmitr.stockControl.itemService.exception.extended.feature.NotFoundFeatureException;
import dmitr.stockControl.itemService.exception.extended.featureListChoice.NotFoundFeatureListChoiceException;
import dmitr.stockControl.itemService.exception.extended.product.NotFoundProductException;
import dmitr.stockControl.itemService.helper.mapper.productFeature.ProductFeatureMapper;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureCreateDto;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureDto;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureViewDto;
import dmitr.stockControl.itemService.model.productFeature.face.ProductFeatureBaseValidation;
import dmitr.stockControl.itemService.service.face.productFeature.ProductFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductFeatureServiceImpl implements ProductFeatureService {

    private final FeatureListChoiceRepository featureListChoiceRepository;
    private final ProductFeatureRepository productFeatureRepository;
    private final ProductRepository productRepository;
    private final FeatureRepository featureRepository;

    private final ProductFeatureMapper productFeatureMapper;

    @Override
    public List<ProductFeatureDto> getProductFeaturesByProductId(UUID productId) {
        productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        List<ProductFeature> productFeatures = productFeatureRepository.findByProductId(productId);

        return productFeatureMapper.toDto(productFeatures);
    }

    @Override
    public List<ProductFeatureViewDto> getProductFeaturesToView(UUID productId) {
        productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        List<ProductFeature> productFeatures = productFeatureRepository.findByProductId(productId);

        return productFeatures.stream()
                .map(productFeature -> {
                    UUID featureId = productFeature.getId().getFeatureId();
                    Feature feature = featureRepository.findById(featureId)
                            .orElseThrow(NotFoundFeatureException::new);

                    String featureName = feature.getName();
                    String featureValue = null;

                    switch (feature.getType()) {
                        case ENUM -> {
                            UUID featureListChoiceId = UUID.fromString(productFeature.getValue());
                            FeatureListChoice listChoice = featureListChoiceRepository.findById(featureListChoiceId)
                                    .orElseThrow(NotFoundFeatureListChoiceException::new);
                            featureValue = listChoice.getValue();
                        }
                        case STRING -> {
                            featureValue = productFeature.getValue();
                        }
                        case BOOLEAN -> {
                            featureValue = Boolean.parseBoolean(productFeature.getValue()) ? "Да" : "Нет";
                        }
                        case NUMBER -> {
                            featureValue = productFeature.getValue() + " " + feature.getUnit();
                        }
                    }

                    return new ProductFeatureViewDto(featureName, featureValue);
                })
                .toList();
    }

    @Override
    public ProductFeatureDto createProductFeature(ProductFeatureCreateDto productFeatureDto) {
        baseValidation(productFeatureDto);

        ProductFeature productFeature = productFeatureMapper.fromDto(productFeatureDto);
        productFeatureRepository.save(productFeature);

        return productFeatureMapper.toDto(productFeature);
    }

    private void baseValidation(ProductFeatureBaseValidation productFeatureBaseValidation) {
        ProductFeatureId productFeatureId = productFeatureBaseValidation.getId();
        productFeatureIdValidation(productFeatureId);

        UUID featureListChoiceId = productFeatureBaseValidation.getFeatureListChoiceId();

        if (featureListChoiceId == null) {
            throw new ValidationException("productFeature.validation.featureListChoice.required");
        }

        UUID featureId = productFeatureId.getFeatureId();

        featureListChoiceRepository.findByIdAndFeatureId(featureListChoiceId, featureId)
                .orElseThrow(NotFoundFeatureListChoiceException::new);
    }

    private void productFeatureIdValidation(ProductFeatureId productFeatureId) {
        if (productFeatureId == null) {
            throw new ValidationException();
        }

        UUID productId = productFeatureId.getProductId();
        if (productId == null) {
            throw new ValidationException("productFeature.validation.product.required");
        }

        UUID featureId = productFeatureId.getFeatureId();
        if (featureId == null) {
            throw new ValidationException("productFeature.validation.feature.required");
        }

        productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        featureRepository.findById(featureId)
                .orElseThrow(NotFoundFeatureException::new);
    }

    @Override
    public void deleteProductFeature(ProductFeatureId productFeatureId) {
        productFeatureIdValidation(productFeatureId);
        productFeatureRepository.deleteById(productFeatureId);
    }
}
