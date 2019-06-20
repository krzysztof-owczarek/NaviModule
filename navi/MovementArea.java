package app.service.navi;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class MovementArea {

    private List<Coordinate> obstacles;
    private int size;
}
