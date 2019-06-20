package app.service.navi;

import io.vavr.Tuple2;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class SimpleRouteService implements RouteService {

    private final List<NextMoveDirectionFilter> filters =
            Arrays.asList(
                    new MoveOppositeToLastDirectionFilter(),
                    new MoveIntoObstacleDirectionFilter(),
                    new MoveBeyondBoardDirectionFilter()
            );


    public SimpleRouteService(List<NextMoveDirectionFilter> customFilters) {
        this.filters.addAll(customFilters);
    }

    @Override
    public List<Coordinate> findRoute(MovementArea movementArea, Coordinate start, Coordinate finish) {
        RouteContext routeContext = new RouteContext(movementArea.getSize(), start, finish, movementArea.getObstacles());
        return findRoute(routeContext);
    }

    private List<Coordinate> findRoute(RouteContext routeContext) {
        if (routeContext.isComplete()) return routeContext.getSteps();

        Set<AxisDirection> directions = directionsForNextMove(routeContext);
        Tuple2<Coordinate, AxisDirection> nextStep = findNextStep(routeContext, directions);
        routeContext.updateRoute(nextStep);

        return findRoute(routeContext);
    }

    private Set<AxisDirection> directionsForNextMove(RouteContext routeContext) {
        Set result = new HashSet(Arrays.asList(AxisDirection.values()));

        for (NextMoveDirectionFilter filter: filters) {
            result.retainAll(filter.apply(routeContext));
        }

        return result;
    }

    private Tuple2<Coordinate, AxisDirection> findNextStep(RouteContext routeContext, Set<AxisDirection> directions) {
        Coordinate currentPosition = routeContext.getCurrentPosition();
        Coordinate destination = routeContext.getDestination();

        if (destination.getX() > currentPosition.getX() && directions.contains(AxisDirection.X_PLUS))
            return new Tuple2<>(Coordinate.getRightFrom(currentPosition), AxisDirection.X_PLUS);

        else if (destination.getX() < currentPosition.getX() && directions.contains(AxisDirection.X_MINUS))
            return new Tuple2<>(Coordinate.getLeftFrom(currentPosition), AxisDirection.X_MINUS);

        else if (destination.getY() > currentPosition.getY() && directions.contains(AxisDirection.Y_PLUS))
            return new Tuple2<>(Coordinate.getUpperFrom(currentPosition), AxisDirection.Y_PLUS);

        else if (destination.getY() < currentPosition.getY() && directions.contains(AxisDirection.Y_MINUS))
            return new Tuple2<>(Coordinate.getBottomFrom(currentPosition), AxisDirection.Y_MINUS);

        return goAroundObstacle(directions, currentPosition);
    }

    private Tuple2<Coordinate, AxisDirection> goAroundObstacle(Set<AxisDirection> moves, Coordinate start) {
        if (moves.contains(AxisDirection.X_PLUS)) return new Tuple2<>(Coordinate.getRightFrom(start), AxisDirection.X_PLUS);
        else if (moves.contains(AxisDirection.X_MINUS)) return new Tuple2<>(Coordinate.getLeftFrom(start), AxisDirection.X_MINUS);
        else if (moves.contains(AxisDirection.Y_PLUS)) return new Tuple2<>(Coordinate.getUpperFrom(start), AxisDirection.Y_PLUS);
        else if (moves.contains(AxisDirection.Y_MINUS)) return new Tuple2<>(Coordinate.getBottomFrom(start), AxisDirection.Y_MINUS);

        throw new UnsupportedOperationException("Cannot find a way around the obstacle!");
    }
}