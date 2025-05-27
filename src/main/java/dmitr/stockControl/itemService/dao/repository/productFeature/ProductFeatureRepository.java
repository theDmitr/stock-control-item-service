package dmitr.stockControl.itemService.dao.repository.productFeature;

import dmitr.stockControl.itemService.dao.entity.productFeature.ProductFeature;
import dmitr.stockControl.itemService.dao.entity.productFeature.ProductFeatureId;
import dmitr.stockControl.itemService.model.productFeature.ProductFeatureViewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductFeatureRepository extends JpaRepository<ProductFeature, ProductFeatureId> {

    @Query("""
            SELECT pf
            FROM ProductFeature pf
            WHERE pf.id.productId = :productId
    """)
    List<ProductFeature> findByProductId(UUID productId);

    @Query("""
            SELECT new dmitr.stockControl.itemService.model.productFeature.ProductFeatureViewDto(
                f.name, flc.value
            )
            FROM ProductFeature pf
                JOIN Feature f ON pf.id.featureId = f.id
                JOIN FeatureListChoice flc ON pf.featureListChoiceId = flc.id
            WHERE pf.id.productId = :productId
    """)
    List<ProductFeatureViewDto> findProductFeaturesToView(UUID productId);
}
