package y2021

import kotlin.math.min

object Day11 {

  private val adjacentSquares = listOf((1 to 0), (1 to -1), (0 to -1), (-1 to -1), (-1 to 0), (-1 to 1), (0 to 1), (1 to 1))

  fun partOne(lines: List<String>): Int {
    val octopodes = lines.map { line -> line.map { Character.getNumericValue(it) } }
    return stepForSteps(octopodes, 100)
  }

  fun partTwo(lines: List<String>): Int {
    val octopodes = lines.map { line -> line.map { Character.getNumericValue(it) } }
    return stepUntilSynchronized(octopodes)
  }

  private fun stepForSteps(octopodes: List<List<Int>>, steps: Int, count: Int = 0): Int {
    if (steps <= 0) return count
    val charged = charge(octopodes)
    return stepForSteps(charged, steps - 1, count + charged.sumOf { row -> row.count { it == 0 } })
  }

  private fun stepUntilSynchronized(octopodes: List<List<Int>>, steps: Int = 1): Int {
    val charged = charge(octopodes)
    if (charged.sumOf { row -> row.count { it == 0 } } == charged.size * charged.size) return steps
    return stepUntilSynchronized(charged, steps + 1)
  }

  private fun charge(octopodes: List<List<Int>>, toCharge: Map<Pair<Int, Int>, Int> = octopodes.indices.flatMap { x -> octopodes.indices.map { y -> (x to y) to 1 } }.toMap()): List<List<Int>> {
    if (toCharge.isEmpty()) return octopodes.map { row -> row.map { if (it >= 10) 0 else it } }

    val charged = octopodes.mapIndexed { x, row ->
      row.mapIndexed { y, octopus ->
        min(octopus + toCharge.getOrDefault(x to y, 0), 10)
      }
    }
    val nextToCharge = toCharge.keys
      .filter { (x, y) -> charged[x][y] == 10 }
      .flatMap { (x, y) -> findAdjacentSquares(charged, x, y) }
      .filter { (x, y) -> charged[x][y] < 10 }
      .groupingBy { it }
      .eachCount()
    return charge(charged, nextToCharge)
  }

  private fun findAdjacentSquares(octopodes: List<List<Int>>, i: Int, j: Int): Set<Pair<Int, Int>> {
    return adjacentSquares.filter { (x, y) -> i + x >= 0 && i + x < octopodes.size && j + y >= 0 && j + y < octopodes.size }
      .map { (x, y) -> i + x to j + y }
      .toSet()
  }
}
