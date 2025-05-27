package dmitr.stockControl.itemService.dao.repository.featureListChoice;

import dmitr.stockControl.itemService.dao.entity.featureListChoice.FeatureListChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeatureListChoiceRepository extends JpaRepository<FeatureListChoice, UUID> {

    List<FeatureListChoice> findByFeatureId(UUID featureId);
    Optional<FeatureListChoice> findByIdAndFeatureId(UUID id, UUID featureId);
}
