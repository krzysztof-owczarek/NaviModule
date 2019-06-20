//package app.service.navi;
//
//import io.vavr.Tuple2;
//import java.util.Arrays;
//import java.util.List;
//import lombok.Getter;
//
//class Route {
//
//    @Getter
//    private List<Coordinate> positions;
//    private AxisDirection lastDirection;
//
//    Route(Coordinate start) {
//        this.positions = Arrays.asList(start);
//    }
//
//    private Route(List<Coordinate> positions, AxisDirection direction) {
//        this.positions = positions;
//        this.lastDirection = direction;
//    }
//
//    public Route update(Tuple2<Coordinate, AxisDirection> nextStep) {
//        positions.add(nextStep._1());
//        lastDirection = nextStep._2();
//
//        return new Route(positions, lastDirection);
//    }
//}