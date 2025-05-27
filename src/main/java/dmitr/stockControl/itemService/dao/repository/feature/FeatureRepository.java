package dmitr.stockControl.itemService.dao.repository.feature;

import dmitr.stockControl.itemService.dao.entity.feature.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, UUID> {
}
