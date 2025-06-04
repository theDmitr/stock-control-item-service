package dmitr.stockControl.itemService.model.category;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CategoryShortDto {

    private UUID id;
    private String name;
}
