package dmitr.stockControl.itemService.dao.repository.maker;

import dmitr.stockControl.itemService.dao.entity.maker.Maker;
import dmitr.stockControl.itemService.model.maker.MakerShortDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MakerRepository extends JpaRepository<Maker, UUID> {

    MakerShortDto findShortById(UUID makerId);
}
