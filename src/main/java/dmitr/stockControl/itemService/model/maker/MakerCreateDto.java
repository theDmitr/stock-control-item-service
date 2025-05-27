package dmitr.stockControl.itemService.model.maker;

import dmitr.stockControl.itemService.model.maker.face.MakerBaseValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MakerCreateDto implements MakerBaseValidation {

    private String name;
}
