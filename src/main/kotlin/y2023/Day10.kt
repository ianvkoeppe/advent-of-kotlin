package y2023

import java.util.*

object Day10 {
  private val pipesSupportingUp: Set<Char> = setOf('|', 'L', 'J', 'S')
  private val pipesSupportingRight: Set<Char> = setOf('-', 'F', 'L', 'S')
  private val pipesSupportingDown: Set<Char> = setOf('|', 'F', '7', 'S')
  private val pipesSupportingLeft: Set<Char> = setOf('-', 'J', '7', 'S')
  private val supportedDirectionToShape: Map<String, Char> =
    mapOf("0011" to '7', "0101" to '-', "0110" to 'F', "1001" to 'J', "1010" to '|', "1100" to 'L')

  private data class Maze(val lines: List<String>) {
    private val maze: List<List<Char>> = lines.map(String::toList)
    private val start: Pos =
      maze.flatMapIndexed { y, row -> List(row.size) { x -> Pos(x, y) } }.first { it.value() == 'S' }
    private val pipes = tracePipes()

    private inner class Pos(val x: Int, val y: Int) {
      fun neighbors(): List<Pos?> = listOf(up(), right(), down(), left())

      private fun up(): Pos? = posIfValid(Pos(x, y - 1), pipesSupportingUp, pipesSupportingDown)

      private fun right(): Pos? = posIfValid(Pos(x + 1, y), pipesSupportingRight, pipesSupportingLeft)

      private fun down(): Pos? = posIfValid(Pos(x, y + 1), pipesSupportingDown, pipesSupportingUp)

      private fun left(): Pos? = posIfValid(Pos(x - 1, y), pipesSupportingLeft, pipesSupportingRight)

      fun interpretedValue(): Char =
        if (value() == 'S')
          supportedDirectionToShape.getValue(Pos(x, y).neighbors().map { if (it != null) 1 else 0 }.joinToString(""))
        else value()

      fun value(): Char = maze[y][x]

      private fun posIfValid(candidate: Pos, validForCurrent: Set<Char>, validForCandidate: Set<Char>): Pos? =
        if (candidate.isInBounds() && value() in validForCurrent && candidate.value() in validForCandidate) candidate
        else null

      private fun isInBounds(): Boolean = y >= 0 && y < maze.size && x >= 0 && x < maze[y].size

      override fun equals(other: Any?): Boolean = other != null && other is Pos && x == other.x && y == other.y

      override fun hashCode(): Int = Objects.hash(x, y)
    }

    private tailrec fun tracePipes(current: Pos? = start, visited: Set<Pos> = setOf()): Set<Pos> {
      if (current == null) return visited

      val next = current.neighbors().filterNotNull().filterNot(visited::contains).firstOrNull()
      return tracePipes(next, visited + current)
    }

    fun countInteriorSpaces(): Int {
      data class RunningCount(
        val intersections: Int = 0,
        val isInterior: Boolean = false,
        val lastSegment: Char? = null,
      )

      return maze
        .flatMapIndexed { y, row ->
          row.indices.runningFold(RunningCount()) { running, x ->
            val v = Pos(x, y).interpretedValue()
            val intersects =
              (Pos(x, y) in pipes && v != '-') &&
                (v != '7' || running.lastSegment != 'L') &&
                (v != 'J' || running.lastSegment != 'F')

            running.copy(
              intersections = running.intersections + if (intersects) 1 else 0,
              isInterior = running.intersections % 2 == 1 && Pos(x, y) !in pipes,
              lastSegment = if (intersects) v else running.lastSegment,
            )
          }
        }
        .count(RunningCount::isInterior)
    }

    fun size(): Int = pipes.size
  }

  fun partOne(lines: List<String>): Int = kotlin.math.ceil(Maze(lines).size() / 2.0).toInt()

  fun partTwo(lines: List<String>): Int = Maze(lines).countInteriorSpaces()
}
