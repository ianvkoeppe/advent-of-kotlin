package y2022

import kotlin.math.abs

object Day22 {
  sealed interface Movement

  class Turn(val direction: Char) : Movement

  class Steps(val times: Int) : Movement

  data class Point(val x: Int, val y: Int) {
    fun manhattanDistance(other: Point): Int = abs(other.x - x) + abs(other.y - y)
  }

  data class LineSegment(val start: Point, val end: Point) {
    fun isHorizontal(): Boolean = start.y == end.y

    fun contains(point: Point): Boolean =
      start.x <= point.x && point.x <= end.x && start.y <= point.y && point.y <= end.y

    fun points(): List<Point> =
      generateSequence(start) { p -> if (isHorizontal()) p.copy(x = p.x + 1) else p.copy(y = p.y + 1) }
        .takeWhile { it != end }
        .toList() + end

    fun pointsBeyondSegment(): Set<Point> =
      if (isHorizontal()) setOf(start.copy(x = start.x - 1), end.copy(x = end.x + 1))
      else setOf(start.copy(y = start.y - 1), end.copy(y = end.y + 1))

    fun isIncreasingOrDecreasingManhattanDistance(other: LineSegment): Boolean =
      isIncreasingManhattanDistance(points(), other.points()) ||
        isIncreasingManhattanDistance(points().reversed(), other.points().reversed())

    private fun isIncreasingManhattanDistance(f: List<Point>, s: List<Point>): Boolean =
      f.zip(s).zipWithNext().all { (f, s) -> f.first.manhattanDistance(f.second) < s.first.manhattanDistance(s.second) }
  }

  enum class Direction {
    EAST,
    SOUTH,
    WEST,
    NORTH;

    fun turn(direction: Char): Direction = if (direction == 'R') turnNinetyDegrees(1) else turnNinetyDegrees(-1)

    fun turnNinetyDegrees(clockwise: Int): Direction =
      Direction.values()[(ordinal + clockwise + Direction.values().size) % Direction.values().size]
  }

  data class Position(val point: Point, val direction: Direction = Direction.EAST) {
    fun findPassword(): Int = 4 * (point.x + 1) + 1000 * (point.y + 1) + direction.ordinal
  }

  data class Board(val points: Map<Point, Char>, val sizeOfEdge: Int) {
    private val adjacentSquares = listOf((1 to 0), (0 to 1), (-1 to 0), (0 to -1))

    fun findStartPosition(): Position =
      Position(Point(points.keys.filter { it.y == 0 }.minOf { it.x }, 0), Direction.EAST)

    fun toMapLinkingOppositeEdge(): BoardMap {
      val edges = findEdges()
      val links =
        findEdges()
          .flatMap { edge ->
            val opposite = edges.filterNot { it == edge }.find { candidate -> isOppositeEdge(edge, candidate) }!!
            val direction = findExitDirection(opposite).turnNinetyDegrees(2)
            edge.points().zip(opposite.points()).map { (f, s) -> Position(f, direction) to Position(s, direction) }
          }
          .toMap()
      return BoardMap(points, links)
    }

    private fun isOppositeEdge(f: LineSegment, s: LineSegment): Boolean =
      when {
        f.isHorizontal() -> f.points().zip(s.points()).all { (f, s) -> f.x == s.x }
        else -> f.points().zip(s.points()).all { (f, s) -> f.y == s.y }
      }

    fun toMapLinkingAs3DCube(linked: Map<LineSegment, LineSegment> = mapOf()): BoardMap {
      val edges = findEdges()
      return when (linked.size) {
        edges.size -> pairedEdgesToPoints(linked)
        edges.size - 2 -> {
          val newlyLinked = edges.filterNot(linked::contains).let { (f, s) -> listOf(f to s, s to f) }.toMap()
          toMapLinkingAs3DCube(linked + newlyLinked)
        }
        else -> {
          val innerCorners =
            edges
              .flatMap(LineSegment::pointsBeyondSegment)
              .filter { point ->
                findNeighbors(point).count { n ->
                  points.contains(n) || edges.any { linked[it]?.contains(point) == true }
                } == 4
              }
              .toSet()
          val toPair =
            innerCorners
              .map { corner ->
                edges
                  .filter { edge ->
                    val overlaps = edges.filterNot { it == edge }.firstOrNull { it.points().any(edge::contains) }
                    findNeighbors(corner).any { n ->
                      edge.contains(n) || (linked[overlaps]?.points() ?: setOf()).contains(n)
                    }
                  }
                  .filterNot(linked::contains)
              }
              .filter { it.size == 2 }

          val newlyLinked = toPair.flatMap { (f, s) -> listOf(f to s, s to f) }.toMap()
          toMapLinkingAs3DCube(linked + newlyLinked)
        }
      }
    }

    private fun pairedEdgesToPoints(edges: Map<LineSegment, LineSegment>): BoardMap {
      val links =
        edges
          .flatMap { (f, s) ->
            val direction = findExitDirection(f)
            val newDirection = findExitDirection(s).turnNinetyDegrees(2)
            val firstOrder =
              if (direction == newDirection || f.isIncreasingOrDecreasingManhattanDistance(s)) f.points()
              else f.points().reversed()
            firstOrder.zip(s.points()).map { (f, s) -> Position(f, direction) to Position(s, newDirection) }
          }
          .toMap()

      return BoardMap(points, links)
    }

    private fun findExitDirection(edge: LineSegment): Direction =
      when {
        edge.points().none { points.contains(it.copy(y = it.y - 1)) } -> Direction.NORTH
        edge.points().none { points.contains(it.copy(x = it.x + 1)) } -> Direction.EAST
        edge.points().none { points.contains(it.copy(y = it.y + 1)) } -> Direction.SOUTH
        else -> Direction.WEST
      }

    private fun findEdges(): Set<LineSegment> {
      val edgePoints = findEdgesPoints()
      return edgePoints.flatMap { edgePoint -> findSegmentContainingPoint(edgePoints, edgePoint) }.toSet()
    }

    private fun findEdgesPoints(): Set<Point> =
      points.keys.filter { point -> findNeighbors(point).count(points::contains) < 4 }.toSet()

    private fun findNeighbors(point: Point): Set<Point> =
      adjacentSquares.map { (adjX, adjY) -> Point(point.x + adjX, point.y + adjY) }.toSet()

    private fun findSegmentContainingPoint(edgePoints: Set<Point>, point: Point): Set<LineSegment> =
      findEdgeSegmentsFromPoints(edgePoints.filter { p -> p.x == point.x }) +
        findEdgeSegmentsFromPoints(edgePoints.filter { p -> p.y == point.y })

    private fun findEdgeSegmentsFromPoints(points: List<Point>): Set<LineSegment> {
      val sorted = points.sortedWith(compareBy({ it.x }, { it.y }))
      val (groups, lastGroup) =
        sorted.drop(1).fold(Pair<Set<Set<Point>>, Set<Point>>(setOf(), setOf(sorted.first()))) { (groups, group), point
          ->
          if (group.last().x + 1 == point.x || group.last().y + 1 == point.y) groups to group + point
          else (groups + setOf(group)) to setOf(point)
        }
      return (groups + setOf(lastGroup))
        .flatMap { group ->
          group
            .chunked(sizeOfEdge)
            .filter { it.size == sizeOfEdge }
            .map { segment -> LineSegment(segment.first(), segment.last()) }
        }
        .toSet()
    }
  }

  data class BoardMap(val points: Map<Point, Char>, val links: Map<Position, Position>) {
    tailrec fun move(position: Position, movements: List<Movement>, movement: Int = 0): Position {
      if (movement == movements.size) return position

      val next =
        when (val move = movements[movement]) {
          is Turn -> position.copy(direction = position.direction.turn(move.direction))
          is Steps -> step(position, move.times)
        }
      return move(next, movements, movement + 1)
    }

    private tailrec fun step(position: Position, steps: Int): Position {
      if (steps == 0) return position

      val next =
        links.getOrElse(position) {
          when (position.direction) {
            Direction.NORTH -> position.copy(point = position.point.copy(y = position.point.y - 1))
            Direction.EAST -> position.copy(point = position.point.copy(x = position.point.x + 1))
            Direction.SOUTH -> position.copy(point = position.point.copy(y = position.point.y + 1))
            else -> position.copy(point = position.point.copy(x = position.point.x - 1))
          }
        }
      return if (points[next.point] == '#') position else step(next, steps - 1)
    }
  }

  fun partOne(lines: List<String>): Int {
    val (board, movements) = parse(lines)
    return board.toMapLinkingOppositeEdge().move(board.findStartPosition(), movements).findPassword()
  }

  fun partTwo(lines: List<String>): Int {
    val (board, movements) = parse(lines)
    return board.toMapLinkingAs3DCube().move(board.findStartPosition(), movements).findPassword()
  }

  private fun parse(lines: List<String>): Pair<Board, List<Movement>> {
    val (board, movements) = lines.joinToString("\n").split("\n\n")
    return parseBoard(board.split("\n")) to parseMovements(movements)
  }

  private fun parseBoard(lines: List<String>): Board {
    val points =
      lines.indices
        .flatMap { y -> lines[y].indices.filterNot { x -> lines[y][x] == ' ' }.map { x -> Point(x, y) to lines[y][x] } }
        .toMap()
    return Board(points, greatestCommonFactor(points.keys.maxOf { it.x + 1 }, points.keys.maxOf { it.y + 1 }))
  }

  private fun greatestCommonFactor(a: Int, b: Int): Int = if (b == 0) a else greatestCommonFactor(b, a % b)

  private fun parseMovements(line: String): List<Movement> {
    val ms =
      line.toList().fold(Pair<List<Movement>, String>(listOf(), "")) { movements, char ->
        if (char.isDigit()) movements.copy(movements.first, movements.second + char)
        else movements.copy(movements.first + Steps(movements.second.toInt()) + Turn(char), "")
      }
    return ms.first + Steps(ms.second.toInt())
  }
}
