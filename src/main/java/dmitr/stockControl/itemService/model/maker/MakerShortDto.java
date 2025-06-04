package dmitr.stockControl.itemService.model.maker;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class MakerShortDto {

    private UUID id;
    private String name;
}
