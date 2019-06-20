package app.service.navi;

import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MoveIntoObstacleDirectionFilter implements NextMoveDirectionFilter {

    @Override
    public Set<AxisDirection> apply(RouteContext routeContext) {
        Set availableMoves = getAllAxisDirections();

        availableMoves.retainAll(filterDirection(Coordinate.getRightFrom(routeContext.getCurrentPosition()), AxisDirection.X_PLUS, routeContext.getObstacles()));
        availableMoves.retainAll(filterDirection(Coordinate.getLeftFrom(routeContext.getCurrentPosition()), AxisDirection.X_MINUS, routeContext.getObstacles()));
        availableMoves.retainAll(filterDirection(Coordinate.getUpperFrom(routeContext.getCurrentPosition()), AxisDirection.Y_PLUS, routeContext.getObstacles()));
        availableMoves.retainAll(filterDirection(Coordinate.getBottomFrom(routeContext.getCurrentPosition()), AxisDirection.Y_MINUS, routeContext.getObstacles()));

        return availableMoves;
    }

    private Set<AxisDirection> filterDirection(Coordinate checkedPosition, AxisDirection direction, List<Coordinate> obstacles) {
        Set<AxisDirection> directions = getAllAxisDirections();

        if (obstacles.contains(checkedPosition)) {
            directions.remove(direction);
        }

        return directions;
    }
}
