package y2023

import java.util.*

object Day16 {
  private enum class Orientation {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    fun prev(): Orientation = Orientation.entries[(ordinal - 1 + entries.size) % entries.size]

    fun next(): Orientation = Orientation.entries[(ordinal + 1) % entries.size]
  }

  private data class Contraption(private val lines: List<String>) {
    private val grid: List<List<Char>> = lines.map(String::toList)

    private inner class Pos(val x: Int, val y: Int, private val orientation: Orientation) {
      fun next(): Set<Pos> {
        val orientations =
          when {
            grid[y][x] == '/' && orientation in setOf(Orientation.UP, Orientation.DOWN) -> setOf(orientation.next())
            grid[y][x] == '/' -> setOf(orientation.prev())
            grid[y][x] == '\\' && orientation in setOf(Orientation.UP, Orientation.DOWN) -> setOf(orientation.prev())
            grid[y][x] == '\\' -> setOf(orientation.next())
            grid[y][x] == '-' && orientation in setOf(Orientation.UP, Orientation.DOWN) ->
              setOf(Orientation.LEFT, Orientation.RIGHT)
            grid[y][x] == '|' && orientation in setOf(Orientation.LEFT, Orientation.RIGHT) ->
              setOf(Orientation.UP, Orientation.DOWN)
            else -> setOf(orientation)
          }

        return orientations
          .map { orientation ->
            when (orientation) {
              Orientation.UP -> Pos(x, y - 1, orientation)
              Orientation.RIGHT -> Pos(x + 1, y, orientation)
              Orientation.DOWN -> Pos(x, y + 1, orientation)
              Orientation.LEFT -> Pos(x - 1, y, orientation)
            }
          }
          .filter(Pos::isInBounds)
          .toSet()
      }

      private fun isInBounds(): Boolean = y >= 0 && y < grid.size && x >= 0 && x < grid[y].size

      override fun equals(other: Any?): Boolean =
        other is Pos && x == other.x && y == other.y && orientation == other.orientation

      override fun hashCode(): Int = Objects.hash(x, y, orientation)
    }

    tailrec fun findLitSpaces(
      unseen: ArrayDeque<Pos> = ArrayDeque(listOf(Pos(0, 0, Orientation.RIGHT))),
      seen: MutableSet<Pos> = mutableSetOf()
    ): Set<Pos> {
      if (unseen.isEmpty()) return seen.distinctBy { pos -> pos.x to pos.y }.toSet()

      val current = unseen.removeFirst()
      seen.add(current)
      unseen.addAll(current.next().filterNot(seen::contains))
      return findLitSpaces(unseen, seen)
    }

    fun findEdges(): Set<Pos> {
      val verticals =
        grid.indices.flatMap { y -> setOf(Pos(0, y, Orientation.RIGHT), Pos(grid[y].size - 1, y, Orientation.LEFT)) }
      val horizontals =
        grid.first().indices.flatMap { x -> setOf(Pos(x, 0, Orientation.DOWN), Pos(x, grid.size - 1, Orientation.UP)) }
      return (verticals + horizontals).toSet()
    }
  }

  fun partOne(lines: List<String>): Int = Contraption(lines).findLitSpaces().size

  fun partTwo(lines: List<String>): Int {
    val contraption = Contraption(lines)
    return contraption.findEdges().maxOf { contraption.findLitSpaces(ArrayDeque(listOf(it))).size }
  }
}
