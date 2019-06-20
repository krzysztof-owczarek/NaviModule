package app.service.navi;

import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MoveBeyondBoardDirectionFilter implements NextMoveDirectionFilter {

    @Override
    public Set<AxisDirection> apply(RouteContext routeContext) {
        Set possibleMoves = getAllAxisDirections();
        Coordinate currentPosition = routeContext.getCurrentPosition();

        if (currentPosition.getX() == 0) possibleMoves.remove(AxisDirection.X_MINUS);
        if (currentPosition.getY() == 0) possibleMoves.remove(AxisDirection.Y_MINUS);

        int lastIndex = routeContext.getSize() - 1;
        if (currentPosition.getX() == lastIndex) possibleMoves.remove(AxisDirection.X_PLUS);
        if (currentPosition.getY() == lastIndex) possibleMoves.remove(AxisDirection.Y_PLUS);


        return possibleMoves;
    }
}
