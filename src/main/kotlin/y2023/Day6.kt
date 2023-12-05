package y2023

import kotlin.math.pow
import kotlin.math.sqrt

object Day6 {
  private data class Race(val time: Long, val distance: Long)

  fun partOne(lines: List<String>): Long = race(lines)

  fun partTwo(lines: List<String>): Long = race(lines) { nums -> listOf(nums.joinToString("").toLong()) }

  private fun race(lines: List<String>, transformer: (List<Long>) -> List<Long> = { it }): Long {
    return parse(lines, transformer)
      .map { race -> solveQuadraticForYIntercepts(-race.time, race.distance) }
      .map { (start, end) -> end - start }
      .reduce(Long::times)
  }

  private fun solveQuadraticForYIntercepts(b: Long, c: Long): Pair<Long, Long> {
    val start = (-b - sqrt(b.toDouble().pow(2) - 4 * c)) / 2
    val end = (-b + sqrt(b.toDouble().pow(2) - 4 * c)) / 2
    return (if (start.toLong().toDouble() == start) start + 1 else start).toLong() to end.toLong()
  }

  private fun parse(lines: List<String>, transformer: (List<Long>) -> List<Long> = { it }): List<Race> {
    val times = transformer(lines.first().splitNumbersAfter("Time: "))
    val distance = transformer(lines.last().splitNumbersAfter("Distance: "))
    return times.zip(distance).map { (time, distance) -> Race(time, distance) }
  }

  private fun String.splitNumbersAfter(prefix: String): List<Long> =
    this.substringAfter(prefix).trim().split("\\s+".toRegex()).map(String::toLong)
}
