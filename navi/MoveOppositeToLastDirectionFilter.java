package app.service.navi;

import java.util.Set;

class MoveOppositeToLastDirectionFilter implements NextMoveDirectionFilter {
    @Override
    public Set<AxisDirection> apply(RouteContext routeContext) {
        Set possibleMoves = getAllAxisDirections();
        AxisDirection lastMove = routeContext.getLastDirection();

        if (lastMove == null) return possibleMoves;

        if (AxisDirection.X_PLUS.equals(lastMove)) possibleMoves.remove(AxisDirection.X_MINUS);
        if (AxisDirection.X_MINUS.equals(lastMove)) possibleMoves.remove(AxisDirection.X_PLUS);
        if (AxisDirection.Y_PLUS.equals(lastMove)) possibleMoves.remove(AxisDirection.Y_MINUS);
        if (AxisDirection.Y_MINUS.equals(lastMove)) possibleMoves.remove(AxisDirection.Y_PLUS);

        return possibleMoves;
    }
}
