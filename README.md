# NaviModule

Simple navigation module for finding route in Cartesian coordinates system.

```java
//TODO: Always find shortest way - more advances implementation
//TODO: Sample project, continuous fixes, more test cases...
```

# Prerequisites
- Java 8+
- Vavr for Tuple2 class
- Lombok for annotations
- Spock for tests

# How it does?
This code provides you with means to find a route (list of coordinates) from point A to point B in Cartisian coordinates system. Moreover it works with obstacles - you give provide their obstacles' possition on the method call.

# How to use it?
For know there is one implementation available - SimpleRouteService.

It uses a simple algorithm for finding route and omitting obstacles in 2D - **it won't give you the sortest path in every situation!**

1. Copy the **navi** package into your project's source folder.
2. Create instance of SimpleRouteService:
```java
RouteService routeService = new SimpleRouteService(); //or new SimpleRouteService(filters) - check out #Filters section
```
3. Call method findRoute with required params and you should receive List<Coordinates> as the result - it's your route! 

# Filters
Filters are implementations of NextMoveDirectionFilter interface.
Every time a new route step is calculated the list of possible directions (right, left, up, bottom) is filtered through the list of declared filters.

There are 3 default filters that are essential for SimpleRouteService implementation:
- **MoveBeyondBoardDirectionFilter** prevents from going outside your playground (guards against ArrayIndexOutOfBoundsException),
- **MoveIntoObstacleDirectionFilter** prevents from crushing into obstacle,
- **MoveOppositeToLastDirectionFilter** prevents from going back to where we were on the last known step.

You can implement other filters and initialize SimpleRouteService implementation with this constructor to use them:
```java
RouteService routeService = new SimpleRouteService(filters);
//TODO: tests
```
