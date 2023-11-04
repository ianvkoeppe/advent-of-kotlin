package y2021

import kotlin.math.abs
import kotlin.math.max

object Day17 {

  fun partOne(lines: List<String>): Int {
    val (_, yRange) = parse(lines)
    return -yRange.first * (-yRange.first - 1) / 2
  }

  fun partTwo(lines: List<String>): Int {
    val (xRange, yRange) = parse(lines)
    return findVelocitiesThatHitTarget(xRange, yRange).size
  }

  private fun parse(lines: List<String>): Pair<IntRange, IntRange> {
    val (xRange, yRange) =
      lines.first().substringAfter(':').split(",").map(String::trim).map {
        val (begin, end) = it.substringAfter("=").split("..").map(String::toInt)
        begin..end
      }
    return xRange to yRange
  }

  private fun findVelocitiesThatHitTarget(xRange: IntRange, yRange: IntRange): Set<Pair<Int, Int>> {
    return (1..xRange.last)
      .flatMap { xVelocity ->
        (yRange.first - 1..abs(yRange.first + 1))
          .filter { yVelocity -> hitsTarget(0, 0, xVelocity, yVelocity, xRange, yRange) }
          .map { yVelocity -> xVelocity to yVelocity }
      }
      .toSet()
  }

  private fun hitsTarget(x: Int, y: Int, xVelocity: Int, yVelocity: Int, xRange: IntRange, yRange: IntRange): Boolean {
    if (xRange.contains(x) && yRange.contains(y)) return true
    if (x >= xRange.last || y < yRange.first) return false

    return hitsTarget(x + xVelocity, y + yVelocity, max(xVelocity - 1, 0), yVelocity - 1, xRange, yRange)
  }
}
