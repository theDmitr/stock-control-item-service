package dmitr.stockControl.itemService.helper.mapper.product;

import dmitr.stockControl.itemService.dao.entity.product.Product;
import dmitr.stockControl.itemService.model.product.ProductCreateDto;
import dmitr.stockControl.itemService.model.product.ProductDto;
import dmitr.stockControl.itemService.model.product.ProductUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);
    List<ProductDto> toDto(List<Product> products);
    Product fromDto(ProductCreateDto productDto);
    Product fromDto(ProductUpdateDto productDto);
}
