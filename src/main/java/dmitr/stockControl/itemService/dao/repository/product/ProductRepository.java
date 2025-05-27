package dmitr.stockControl.itemService.dao.repository.product;

import dmitr.stockControl.itemService.dao.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
