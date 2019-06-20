package app.service.navi

import org.assertj.core.util.Lists
import spock.lang.Specification

class SimpleRouteServiceSpec extends Specification {

    RouteService routeService

    def 'can find route without obstacles on X axis'() {
        given:
        Coordinate start = new Coordinate(0, 0)
        and:
        Coordinate finish = new Coordinate(3, 0)
        and:
        MovementArea movementArea = MovementArea.builder().size(5).obstacles(Lists.emptyList()).build()
        and:
        routeService = new SimpleRouteService()

        when:
        def route = routeService.findRoute(movementArea, start, finish)

        then:
        route.containsAll(
                start,
                new Coordinate(1, 0),
                new Coordinate(2, 0),
                finish
        )
    }

    def 'can find route without obstacles on Y axis'() {
        given:
        Coordinate start = new Coordinate(0, 1)
        and:
        Coordinate finish = new Coordinate(0, 3)
        and:
        MovementArea movementArea = MovementArea.builder().size(5).obstacles(Lists.emptyList()).build()
        and:
        routeService = new SimpleRouteService()

        when:
        def route = routeService.findRoute(movementArea, start, finish)

        then:
        route.containsAll(
                start,
                new Coordinate(0, 1),
                finish
        )
    }

    def 'can find route around obstacle Y on the PLUS X'() {
        given:
        Coordinate start = new Coordinate(0, 0)
        and:
        Coordinate finish = new Coordinate(0, 3)
        and:
        MovementArea movementArea = MovementArea.builder().size(5).obstacles(Arrays.asList(new Coordinate(0, 1))).build()
        and:
        routeService = new SimpleRouteService()

        when:
        def route = routeService.findRoute(movementArea, start, finish)

        then:
        route == Arrays.asList(
                start,
                new Coordinate(1, 0),
                new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(0, 2),
                finish
        )
    }

    def 'can find route around obstacle Y on the MINUS X'() {
        given:
        Coordinate start = new Coordinate(3, 0)
        and:
        Coordinate finish = new Coordinate(0, 0)
        and:
        MovementArea movementArea = MovementArea.builder().size(5).obstacles(Arrays.asList(new Coordinate(1, 0))).build()
        and:
        routeService = new SimpleRouteService()

        when:
        def route = routeService.findRoute(movementArea, start, finish)

        then:
        route == Arrays.asList(
                start,
                new Coordinate(2, 0),
                new Coordinate(2, 1),
                new Coordinate(1,1),
                new Coordinate(0, 1),
                finish
        )
    }

    def 'can find route around obstacles side by side'() {
        given:
        Coordinate start = new Coordinate(0, 0)
        and:
        Coordinate finish = new Coordinate(2, 2)
        and:
        MovementArea movementArea = MovementArea.builder().size(5).obstacles(Arrays.asList(new Coordinate(1, 0),
        new Coordinate(1, 1))).build()
        and:
        routeService = new SimpleRouteService()

        when:
        def route = routeService.findRoute(movementArea, start, finish)

        then:
        route == Arrays.asList(
                start,
                new Coordinate(0, 1),
                new Coordinate(0, 2),
                new Coordinate(1,2),
                finish
        )
    }
}
