package app.service.navi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

interface NextMoveDirectionFilter {

    default Set<AxisDirection> getAllAxisDirections() {
        return new HashSet<>(Arrays.asList(AxisDirection.values()));
    }

    Set<AxisDirection> apply(RouteContext routeContext);
}
