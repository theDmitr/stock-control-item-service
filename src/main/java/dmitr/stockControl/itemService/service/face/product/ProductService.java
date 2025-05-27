package dmitr.stockControl.itemService.service.face.product;

import dmitr.stockControl.itemService.model.product.ProductCreateDto;
import dmitr.stockControl.itemService.model.product.ProductDto;
import dmitr.stockControl.itemService.model.product.ProductUpdateDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDto> getProducts();
    ProductDto getProduct(UUID id);
    ProductDto createProduct(ProductCreateDto productDto);
    ProductDto updateProduct(UUID productId, ProductUpdateDto productDto);
    void deleteProduct(UUID id);
}
