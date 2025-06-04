package dmitr.stockControl.itemService.dao.repository.category;

import dmitr.stockControl.itemService.dao.entity.category.Category;
import dmitr.stockControl.itemService.model.category.CategoryPageViewDto;
import dmitr.stockControl.itemService.model.category.CategoryShortDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    CategoryShortDto findShortById(UUID categoryId);

    @Query(value = """
            WITH RECURSIVE category_tree AS (
                SELECT id, parentCategoryId
                FROM categories
                WHERE id = uuid_to_bin(:parentCategoryId)
                UNION ALL
                SELECT c.id, c.parentCategoryId
                FROM categories c
                INNER JOIN category_tree ct ON c.parentCategoryId = ct.id
            )
            SELECT bin_to_uuid(id)
            FROM category_tree;
    """, nativeQuery = true)
    List<UUID> findSubCategoriesByCategoryId(String parentCategoryId);

    @Query(value = """
            WITH RECURSIVE category_tree(id, sub_id) AS (
                SELECT id, id
                FROM categories
                UNION ALL
                SELECT ct.id, c.id
                FROM categories c
                INNER JOIN category_tree ct ON c.parentCategoryId = ct.sub_id
            )
            SELECT
                bin_to_uuid(c.id),
                c.name,
                count(p.id),
                c.image,
                COUNT(ct.id),
                bin_to_uuid(c.parentCategoryId)
            FROM category_tree ct
            JOIN categories c ON c.id = ct.id
            LEFT JOIN products p ON p.categoryId = ct.sub_id
            GROUP BY ct.id, c.name
            ORDER BY c.name
    """, nativeQuery = true)
    List<CategoryPageViewDto> findAllToPageView();
}
