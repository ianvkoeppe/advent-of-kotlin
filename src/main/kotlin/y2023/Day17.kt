package y2023

import java.util.*

object Day17 {
  private data class Pos(val x: Int, val y: Int)

  private enum class Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT,
  }

  private data class Crucible(val pos: Pos, val last: Direction? = null, val consecutiveSteps: Int = 0) {
    fun move(pos: Pos, direction: Direction): Crucible =
      copy(pos = pos, last = direction, consecutiveSteps = if (direction == last) consecutiveSteps + 1 else 1)
  }

  private class City(lines: List<String>, private val min: Int, private val max: Int) {
    private val movements: Map<Direction, (Pos, Int) -> Pos> =
      mapOf(
        Direction.UP to { c, n -> c.copy(y = c.y - n) },
        Direction.RIGHT to { c, n -> c.copy(x = c.x + n) },
        Direction.DOWN to { c, n -> c.copy(y = c.y + n) },
        Direction.LEFT to { c, n -> c.copy(x = c.x - n) },
      )
    private val opposite: Map<Direction, Direction> =
      mapOf(
        Direction.UP to Direction.DOWN,
        Direction.RIGHT to Direction.LEFT,
        Direction.DOWN to Direction.UP,
        Direction.LEFT to Direction.RIGHT,
      )

    private val grid: List<List<Int>> = lines.map { it.map(Char::digitToInt) }

    fun findMinHeatLossPath(start: Pos = Pos(0, 0), end: Pos = Pos(grid[0].size - 1, grid.size - 1)): Int {
      val shortest = mutableMapOf(Crucible(start) to 0)
      val queue = PriorityQueue(compareBy(shortest::getValue)).also { it.add(Crucible(start)) }

      while (queue.isNotEmpty()) {
        val current = queue.poll()

        findAllowedMovements(current).filterNot(shortest::containsKey).forEach { next ->
          shortest[next] = shortest.getValue(current) + grid[next.pos.y][next.pos.x]
          queue.add(next)
        }
      }

      return shortest.filterKeys { it.pos == end }.minOf { it.value }
    }

    private fun findAllowedMovements(crucible: Crucible): List<Crucible> =
      listOfNotNull(
        move(crucible, Direction.UP),
        move(crucible, Direction.RIGHT),
        move(crucible, Direction.DOWN),
        move(crucible, Direction.LEFT),
      )

    private fun move(crucible: Crucible, direction: Direction): Crucible? {
      val isContinuingAndNotMetMaximum = crucible.last in setOf(null, direction) && crucible.consecutiveSteps < max
      val isTurningAndMetMinimum =
        crucible.last !in setOf(direction, opposite.getValue(direction)) &&
          crucible.consecutiveSteps >= min &&
          isInBounds(movements.getValue(direction)(crucible.pos, min))

      val next = movements.getValue(direction)(crucible.pos, 1)
      return if (isInBounds(next) && (isContinuingAndNotMetMaximum || isTurningAndMetMinimum))
        crucible.move(next, direction)
      else null
    }

    private fun isInBounds(pos: Pos): Boolean = pos.y >= 0 && pos.y < grid.size && pos.x >= 0 && pos.x < grid[0].size
  }

  fun partOne(lines: List<String>): Int = City(lines, min = 0, max = 3).findMinHeatLossPath()

  fun partTwo(lines: List<String>): Int = City(lines, min = 4, max = 10).findMinHeatLossPath()
}
