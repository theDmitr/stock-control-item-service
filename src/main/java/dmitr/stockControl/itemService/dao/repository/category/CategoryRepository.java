package dmitr.stockControl.itemService.dao.repository.category;

import dmitr.stockControl.itemService.dao.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
