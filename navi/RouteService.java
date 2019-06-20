package app.service.navi;

import java.util.List;

public interface RouteService {
    List<Coordinate> findRoute(MovementArea movementArea, Coordinate start, Coordinate finish);
}
