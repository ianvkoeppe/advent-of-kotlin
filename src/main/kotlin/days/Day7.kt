package days

import kotlin.math.abs

object Day7 {

  fun partOne(lines: List<String>): Int {
    val positions = lines.first().split(",").map { it.toInt() }.sorted()
    return findMinFuelWithBinarySearch(positions) { a, b -> abs(a - b) }
  }

  fun partTwo(lines: List<String>): Int {
    val positions = lines.first().split(",").map { it.toInt() }.sorted()
    return findMinFuelWithBinarySearch(positions) { a, b -> (abs(a - b) * (abs(a - b) + 1)) / 2 }
  }

  private fun findMinFuelWithBinarySearch(positions: List<Int>, calculation: (Int, Int) -> Int): Int {
    val alignment = abs(((positions.minOrNull() ?: 0)..(positions.maxOrNull() ?: 0)).zipWithNext().binarySearch { (current, next) ->
      positions.sumOf { calculation(it, next) }.compareTo(positions.sumOf { calculation(it, current) })
    }) - 1 // Returns (-insertion point - 1)
    return positions.sumOf { calculation(it, alignment) }
  }
}
