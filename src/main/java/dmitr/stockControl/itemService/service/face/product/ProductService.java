package dmitr.stockControl.itemService.service.face.product;

import dmitr.stockControl.itemService.controller.product.request.ProductSearchFilterDto;
import dmitr.stockControl.itemService.controller.product.response.ProductStockResponseDto;
import dmitr.stockControl.itemService.model.product.*;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDto> getProducts();
    ProductDto getProduct(UUID id);
    List<ProductPageViewDto> getProductsToPage(ProductsToPageFilterDto filter);
    List<ProductStockResponseDto> getProductsToStockByFilter(ProductSearchFilterDto filter);
    ProductInfoDto getProductInfo(UUID productId);
    ProductDto createProduct(ProductCreateDto productDto);
    ProductDto updateProduct(UUID productId, ProductUpdateDto productDto);
    void deleteProduct(UUID id);
}
