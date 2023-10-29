package y2021

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.min

object Day7 {

  fun partOne(lines: List<String>): Int {
    val positions = lines.first().split(",").map { it.toInt() }.sorted()
    return positions.sumOf { abs(it - positions[positions.size / 2]) }
  }

  fun partTwo(lines: List<String>): Int {
    val positions = lines.first().split(",").map { it.toInt() }
    val floor =
      positions.sumOf {
        val n = abs(it - floor(positions.average()).toInt())
        n * (n + 1) / 2
      }
    val ceil =
      positions.sumOf {
        val n = abs(it - ceil(positions.average()).toInt())
        n * (n + 1) / 2
      }
    return min(floor, ceil)
  }
}
