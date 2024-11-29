package y2022

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Day15 {
  fun Pair<Int, Int>.merge(other: Pair<Int, Int>): List<Pair<Int, Int>> =
    if (intersects(other)) listOf(min(first, other.first) to max(second, other.second)) else listOf(this, other)

  private fun Pair<Int, Int>.intersects(other: Pair<Int, Int>): Boolean = first <= other.second && second >= other.first

  private data class Point(val x: Int, val y: Int) {
    fun manhattanDistance(other: Point) = abs(x - other.x) + abs(y - other.y)

    fun tuningFrequency(): Long = x.toLong() * 4000000 + y.toLong()
  }

  private data class IdentifiedSignal(
    val sensor: Point,
    val beacon: Point,
    val height: Int = sensor.manhattanDistance(beacon),
  ) {
    private val adjacentSquares = listOf((1 to 0), (0 to 1), (-1 to 0), (0 to -1))

    fun findPointsOutOfRange(min: Int, max: Int): List<Point> {
      return (sensor.y - height until sensor.y + height).flatMap { y ->
        findRangeOfSignalAt(y)?.let { (start, end) ->
          listOf(Point(start, y), Point(end, y)).flatMap { (x, y) ->
            adjacentSquares
              .map { (adjX, adjY) -> Point(x + adjX, y + adjY) }
              .filter { (x, y) -> x in min..max && y in min..max }
          }
        } ?: listOf()
      }
    }

    fun findRangeOfSignalAt(y: Int): Pair<Int, Int>? {
      val remainingDistance = height - sensor.manhattanDistance(Point(sensor.x, y))
      return if (remainingDistance >= 0) (sensor.x - remainingDistance) to (sensor.x + remainingDistance) else null
    }

    fun isInSignalRange(point: Point): Boolean = sensor.manhattanDistance(point) <= height

    fun overlapsWithBeacon(range: Pair<Int, Int>, y: Int): Boolean =
      range.first <= beacon.x && beacon.x <= range.second && beacon.y == y
  }

  private data class Signals(val signals: List<IdentifiedSignal>) {
    fun countRangeOfSignalsAt(y: Int): Int {
      val ranges = findRangeOfSignalsAt(y)
      return ranges.sumOf { (start, end) -> end - start + 1 } - countOverlappingBeacons(ranges, y)
    }

    fun findDistressBeacon(max: Int): Point {
      return signals
        .asSequence()
        .flatMap { signal -> signal.findPointsOutOfRange(0, max) }
        .filterNot { point -> signals.any { it.isInSignalRange(point) } }
        .first()
    }

    private fun findRangeOfSignalsAt(y: Int): List<Pair<Int, Int>> {
      val ranges = signals.mapNotNull { it.findRangeOfSignalAt(y) }.sortedBy { it.first }
      return ranges.drop(1).fold(listOf(ranges.first())) { merged, range -> merged.flatMap { it.merge(range) } }
    }

    private fun countOverlappingBeacons(ranges: List<Pair<Int, Int>>, y: Int): Int =
      ranges.count { range -> signals.any { it.overlapsWithBeacon(range, y) } }
  }

  fun partOne(lines: List<String>, y: Int): Int = parse(lines).countRangeOfSignalsAt(y)

  fun partTwo(lines: List<String>, max: Int): Long = parse(lines).findDistressBeacon(max).tuningFrequency()

  private fun parse(lines: List<String>): Signals {
    return Signals(
      lines
        .map { line -> line.split(": ") }
        .map { (sensorLine, beaconLine) ->
          val sensor = parseCoordinates(sensorLine.removePrefix("Sensor at "))
          val beacon = parseCoordinates(beaconLine.removePrefix("closest beacon is at "))
          IdentifiedSignal(sensor, beacon)
        }
    )
  }

  private fun parseCoordinates(coordinates: String): Point {
    val (x, y) = coordinates.split(", ")
    return Point(x.removePrefix("x=").toInt(), y.removePrefix("y=").toInt())
  }
}
