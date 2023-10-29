package y2022

import kotlin.math.abs

object Day23 {
  data class Point(val x: Int, val y: Int)

  data class Crater(val elves: Set<Point>, val rounds: Int = 0) {
    private val surroundingSquares =
      listOf(1 to 0, 1 to -1, 0 to -1, -1 to -1, -1 to 0, -1 to 1, 0 to 1, 1 to 1)
    private val north = surroundingSquares.filter { (_, y) -> y == -1 }
    private val south = surroundingSquares.filter { (_, y) -> y == 1 }
    private val west = surroundingSquares.filter { (x, _) -> x == -1 }
    private val east = surroundingSquares.filter { (x, _) -> x == 1 }
    private val directions = listOf(north, south, west, east)

    fun optimizePlanting(round: Int = -1, directionPriority: Int = 0): Crater {
      if (round == 0) return this

      val proposals = findProposedMoves(directionPriority)
      val uniqueDestinations = proposals.values.groupingBy { it }.eachCount()
      val movements =
        proposals
          .mapValues { d -> if (uniqueDestinations.getValue(d.value) == 1) d.value else d.key }
          .map { it.value }
          .toSet()

      return when (elves) {
        movements -> copy(rounds = rounds + 1)
        else ->
          copy(elves = movements, rounds = rounds + 1)
            .optimizePlanting(
              round - 1,
              directionPriority = (directionPriority + 1) % directions.size
            )
      }
    }

    private fun findProposedMoves(directionPriority: Int): Map<Point, Point> =
      elves.associateWith { elf ->
        when {
          findAdjacentSquares(surroundingSquares, elf).none { elves.contains(it) } -> elf
          else -> findProposedMove(elf, directionPriority) ?: elf
        }
      }

    private fun findProposedMove(elf: Point, directionPriority: Int): Point? {
      return directions.indices
        .asSequence()
        .map { directions[(it + directionPriority) % directions.size] }
        .map { direction -> findAdjacentSquares(direction, elf) }
        .firstOrNull { squares -> squares.none(elves::contains) }
        ?.find { (newX, newY) -> abs(newX - elf.x) + abs(newY - elf.y) == 1 }
    }

    fun findEmptyGroundWithinElves(): Int =
      findYRange().sumOf { y -> findXRange().count { x -> !elves.contains(Point(x, y)) } }

    private fun findXRange(): IntRange = elves.minOf { it.x }..elves.maxOf { it.x }

    private fun findYRange(): IntRange = elves.minOf { it.y }..elves.maxOf { it.y }

    private fun findAdjacentSquares(directions: List<Pair<Int, Int>>, point: Point): List<Point> =
      directions.map { (adjX, adjY) -> Point(point.x + adjX, point.y + adjY) }
  }

  fun partOne(lines: List<String>): Int =
    parse(lines).optimizePlanting(10).findEmptyGroundWithinElves()

  fun partTwo(lines: List<String>): Int = parse(lines).optimizePlanting().rounds

  private fun parse(lines: List<String>): Crater {
    return Crater(
      lines.indices
        .flatMap { y ->
          lines[y].indices.filter { x -> lines[y][x] == '#' }.map { x -> Point(x, y) }
        }
        .toSet()
    )
  }
}
