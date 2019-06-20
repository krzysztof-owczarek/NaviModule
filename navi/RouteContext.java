package app.service.navi;

import io.vavr.Tuple2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
class RouteContext {
    private final Integer size;
    private final Coordinate destination;
    private final List<Coordinate> obstacles;

    private Coordinate currentPosition;
    private Route route;

    public RouteContext(Integer size, Coordinate currentPosition, Coordinate destination, List<Coordinate> obstacles) {
        this.size = size;
        this.currentPosition = currentPosition;
        this.destination = destination;
        this.obstacles = obstacles;

        if (this.size == null || this.currentPosition == null || this.destination == null) {
            throw new IllegalStateException("One of the parameters is null.");
        }

        this.route = new Route(currentPosition);
    }

    void updateRoute(Tuple2<Coordinate, AxisDirection> nextMove) {
        route = route.update(nextMove);
        currentPosition = nextMove._1();
    }

    boolean isComplete() {
        return currentPosition.equals(destination);
    }

    List<Coordinate> getSteps() {
        if (!isComplete()) throw new UnsupportedOperationException("Steps can be accessed only when the route has been found!");
        return route.steps;
    }

    AxisDirection getLastDirection() {
        return route.lastDirection;
    }

    private class Route {

        private List<Coordinate> steps;
        private AxisDirection lastDirection;

        Route(Coordinate start) {
            this.steps = new ArrayList<>(Arrays.asList(start));
        }

        private Route(List<Coordinate> steps, AxisDirection direction) {
            this.steps = steps;
            this.lastDirection = direction;
        }

        public Route update(Tuple2<Coordinate, AxisDirection> nextStep) {
            steps.add(nextStep._1());
            lastDirection = nextStep._2();

            return new Route(steps, lastDirection);
        }
    }
}