package dmitr.stockControl.itemService.service.impl.product;

import dmitr.stockControl.itemService.controller.product.request.ProductSearchFilterDto;
import dmitr.stockControl.itemService.controller.product.response.ProductStockResponseDto;
import dmitr.stockControl.itemService.dao.entity.product.Product;
import dmitr.stockControl.itemService.dao.entity.product.ProductImage;
import dmitr.stockControl.itemService.dao.repository.category.CategoryRepository;
import dmitr.stockControl.itemService.dao.repository.maker.MakerRepository;
import dmitr.stockControl.itemService.dao.repository.product.ProductRepository;
import dmitr.stockControl.itemService.exception.extended.ValidationException;
import dmitr.stockControl.itemService.exception.extended.category.NotFoundCategoryException;
import dmitr.stockControl.itemService.exception.extended.feature.NotFoundFeatureException;
import dmitr.stockControl.itemService.exception.extended.maker.NotFoundMakerException;
import dmitr.stockControl.itemService.exception.extended.product.NotFoundProductException;
import dmitr.stockControl.itemService.helper.mapper.product.ProductMapper;
import dmitr.stockControl.itemService.model.category.CategoryShortDto;
import dmitr.stockControl.itemService.model.maker.MakerShortDto;
import dmitr.stockControl.itemService.model.product.*;
import dmitr.stockControl.itemService.model.product.face.ProductBaseValidation;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureViewDto;
import dmitr.stockControl.itemService.service.face.product.ProductService;
import dmitr.stockControl.itemService.service.face.productFeature.ProductFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static dmitr.stockControl.itemService.utils.Constants.EMPTY_IMAGE_URL;
import static io.micrometer.common.util.StringUtils.isBlank;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final MakerRepository makerRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final ProductFeatureService productFeatureService;

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
    public List<ProductPageViewDto> getProductsToPage(ProductsToPageFilterDto filter) {
        UUID parentCategoryId = filter.getCategoryId();

        List<UUID> parentCategoriesIds;
        if (parentCategoryId != null) {
            parentCategoriesIds = categoryRepository.findSubCategoriesByCategoryId(parentCategoryId.toString());
        } else {
            parentCategoriesIds = new ArrayList<>();
        }

        List<Product> products = productRepository.findAll()
                .stream()
                .filter(p -> {
                    if (filter.getSearchName() != null) {
                        if (!p.getName().toLowerCase().contains(filter.getSearchName().toLowerCase())) {
                            return false;
                        }
                    }
                    if (parentCategoryId != null) {
                        return p.getCategoryId() != null && parentCategoriesIds.contains(p.getCategoryId());
                    }
                    return true;
                })
                .toList();

        List<ProductPageViewDto> productsToPage = new ArrayList<>();
        for (Product product : products) {
            ProductPageViewDto productPageViewDto = ProductPageViewDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .images(getProductImageLinks(product))
                    .build();
            productsToPage.add(productPageViewDto);
        }

        return productsToPage;
    }

    @Override
    public List<ProductStockResponseDto> getProductsToStockByFilter(ProductSearchFilterDto filter) {
        return productRepository.findBySearchFilter(filter)
                .stream()
                .map(p -> ProductStockResponseDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .image(getProductImageLinks(p).getLast())
                        .build()
                )
                .toList();
    }

    @Override
    public ProductInfoDto getProductInfo(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        CategoryShortDto category = categoryRepository.findShortById(product.getCategoryId());
        MakerShortDto maker = makerRepository.findShortById(product.getMakerId());
        List<ProductFeatureViewDto> features = productFeatureService.getProductFeaturesToView(productId);
        List<String> productImagesLinks = getProductImageLinks(product);

        return ProductInfoDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(category)
                .maker(maker)
                .rating(0)
                .images(productImagesLinks)
                .features(features)
                .build();
    }

    private List<String> getProductImageLinks(Product product) {
        List<String> productImagesLinks = product.getImages()
                .stream()
                .sorted(Comparator.comparingInt(ProductImage::getIndex))
                .map(ProductImage::getLink)
                .collect(Collectors.toList());

        if (productImagesLinks.isEmpty()) {
            productImagesLinks.add(EMPTY_IMAGE_URL);
        }

        return productImagesLinks;
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
