package y2023

import java.util.*

object Day23 {
  private class Trails(lines: List<String>, private val allowedMovement: (Char, Char) -> Boolean) {
    private val grid: List<List<Char>> = lines.map(String::toList)
    private val start: Pos = Pos(grid.first().indexOf('.'), 0)
    private val end: Pos = Pos(grid.last().indexOf('.'), grid.size - 1)
    private val intersections: Set<Pos> =
      grid.indices.flatMap { y -> grid[y].indices.map { x -> Pos(x, y) } }.filter(Pos::isIntersection).toSet() +
        setOf(start, end)
    private val routes: Map<Pos, List<Route>> =
      intersections.associateWith { intersection ->
        intersection.validNeighbors().mapNotNull { createRoute(it, setOf(intersection)) }
      }

    private inner class Pos(val x: Int, val y: Int) {
      fun isIntersection(): Boolean =
        listOf(up(), right(), down(), left()).count { pos -> pos.isInBounds() && grid[pos.y][pos.x] != '#' } > 2

      fun validNeighbors(): List<Pos> =
        listOfNotNull(up().validOrNull('^'), right().validOrNull('>'), down().validOrNull('v'), left().validOrNull('<'))

      fun up(): Pos = Pos(x, y - 1)

      fun right(): Pos = Pos(x + 1, y)

      fun down(): Pos = Pos(x, y + 1)

      fun left(): Pos = Pos(x - 1, y)

      private fun validOrNull(allowedSlope: Char): Pos? =
        if (isInBounds() && allowedMovement(grid[y][x], allowedSlope)) this else null

      private fun isInBounds(): Boolean = y >= 0 && y < grid.size && x >= 0 && x < grid[y].size

      override fun equals(other: Any?): Boolean = other is Pos && x == other.x && y == other.y

      override fun hashCode(): Int = Objects.hash(x, y)
    }

    private data class Route(val end: Pos, val length: Int)

    private fun createRoute(current: Pos, seen: Set<Pos>): Route? =
      if (current in intersections) Route(current, seen.size)
      else current.validNeighbors().firstOrNull { it !in seen }?.let { createRoute(it, seen + current) }

    fun findLongestPath(current: Pos = start, seen: Set<Pos> = setOf(), distance: Int = 0): Int {
      if (current == end) return distance

      val next = routes.getValue(current).filterNot { it.end in seen }
      return if (next.isNotEmpty()) next.maxOf { findLongestPath(it.end, seen + current, distance + it.length) } else -1
    }
  }

  fun partOne(lines: List<String>): Int =
    Trails(lines) { space, allowedSlope -> space in setOf('.', allowedSlope) }.findLongestPath()

  fun partTwo(lines: List<String>): Int = Trails(lines) { space, _ -> space != '#' }.findLongestPath()
}
