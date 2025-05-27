package dmitr.stockControl.itemService.model.maker;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class MakerDto {

    private UUID id;
    private String name;
}
