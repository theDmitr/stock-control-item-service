package dmitr.stockControl.itemService.dao.entity.productFeature;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products_features")
public class ProductFeature {

    @EmbeddedId
    private ProductFeatureId id;

    @Column(name = "value", nullable = false)
    private String value;
}
