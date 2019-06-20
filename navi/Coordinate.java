package app.service.navi;

import lombok.Value;

@Value
public class Coordinate {
    private final int x;
    private final int y;

    public static Coordinate getRightFrom(Coordinate coordinate) {
        return new Coordinate(coordinate.x+1, coordinate.y);
    }

    public static Coordinate getLeftFrom(Coordinate coordinate) {
        return new Coordinate(coordinate.x-1, coordinate.y);
    }

    public static Coordinate getUpperFrom(Coordinate coordinate) {
        return new Coordinate(coordinate.x, coordinate.y+1);
    }

    public static Coordinate getBottomFrom(Coordinate coordinate) {
        return new Coordinate(coordinate.x, coordinate.y-1);
    }
}
