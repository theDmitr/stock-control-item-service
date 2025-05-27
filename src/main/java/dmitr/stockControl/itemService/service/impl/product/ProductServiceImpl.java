package dmitr.stockControl.itemService.service.impl.product;

import dmitr.stockControl.itemService.dao.entity.product.Product;
import dmitr.stockControl.itemService.dao.repository.category.CategoryRepository;
import dmitr.stockControl.itemService.dao.repository.maker.MakerRepository;
import dmitr.stockControl.itemService.dao.repository.product.ProductRepository;
import dmitr.stockControl.itemService.exception.extended.ValidationException;
import dmitr.stockControl.itemService.exception.extended.category.NotFoundCategoryException;
import dmitr.stockControl.itemService.exception.extended.feature.NotFoundFeatureException;
import dmitr.stockControl.itemService.exception.extended.maker.NotFoundMakerException;
import dmitr.stockControl.itemService.exception.extended.product.NotFoundProductException;
import dmitr.stockControl.itemService.helper.mapper.product.ProductMapper;
import dmitr.stockControl.itemService.model.product.ProductCreateDto;
import dmitr.stockControl.itemService.model.product.ProductDto;
import dmitr.stockControl.itemService.model.product.ProductUpdateDto;
import dmitr.stockControl.itemService.model.product.face.ProductBaseValidation;
import dmitr.stockControl.itemService.service.face.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static io.micrometer.common.util.StringUtils.isBlank;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final MakerRepository makerRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDto(products);
    }

    @Override
    public ProductDto getProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(NotFoundProductException::new);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto createProduct(ProductCreateDto productDto) {
        baseValidation(productDto);

        Product product = productMapper.fromDto(productDto);
        productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Override
    public ProductDto updateProduct(UUID productId, ProductUpdateDto productDto) {
        productRepository.findById(productId)
                .orElseThrow(NotFoundFeatureException::new);

        baseValidation(productDto);

        Product product = productMapper.fromDto(productDto);
        product.setId(productId);
        productRepository.save(product);

        return productMapper.toDto(product);
    }

    private void baseValidation(ProductBaseValidation product) {
        String name = product.getName();
        if (isBlank(name)) {
            throw new ValidationException("product.validation.name.required");
        }

        String description = product.getDescription();
        if (isBlank(description)) {
            throw new ValidationException("product.validation.description.required");
        }

        UUID categoryId = product.getCategoryId();
        if (categoryId != null) {
            categoryRepository.findById(categoryId)
                    .orElseThrow(NotFoundCategoryException::new);
        }

        UUID makerId = product.getMakerId();
        if (makerId != null) {
            makerRepository.findById(makerId)
                    .orElseThrow(NotFoundMakerException::new);
        }
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id); // todo валидация, не используется ли где-то в других сервисах
    }
}
