package dmitr.stockControl.itemService.dao.entity.featureListChoice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "features_list_choices")
public class FeatureListChoice {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "feature_id", nullable = false)
    private UUID featureId;

    @Column(name = "value", nullable = false, length = 256)
    private String value;
}
