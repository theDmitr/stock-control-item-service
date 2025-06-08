package dmitr.stockControl.itemService.dao.repository.product;

import dmitr.stockControl.itemService.controller.product.request.ProductSearchFilterDto;
import dmitr.stockControl.itemService.dao.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("""
            from Product p
            where coalesce(:#{#filter.productIds}, null) is null or p.id in :#{#filter.productIds}
    """)
    List<Product> findBySearchFilter(ProductSearchFilterDto filter);
}
