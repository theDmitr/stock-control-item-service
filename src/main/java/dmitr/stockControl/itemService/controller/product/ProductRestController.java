package dmitr.stockControl.itemService.controller.product;

import dmitr.stockControl.itemService.model.product.ProductCreateDto;
import dmitr.stockControl.itemService.model.product.ProductDto;
import dmitr.stockControl.itemService.model.product.ProductUpdateDto;
import dmitr.stockControl.itemService.service.face.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {
    
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable UUID productId) {
        return productService.getProduct(productId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ProductDto createProduct(@RequestBody ProductCreateDto productDto) {
        return productService.createProduct(productDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable UUID productId,
                                    @RequestBody ProductUpdateDto productDto) {
        return productService.updateProduct(productId, productDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
    }
}
