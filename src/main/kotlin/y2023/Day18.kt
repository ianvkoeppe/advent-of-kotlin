package y2023

import kotlin.math.min

object Day18 {
  private data class Instruction(val direction: Char, val amount: Int)

  private data class Point(val x: Long, val y: Long)

  private data class LineSegment(val start: Point, val end: Point) {
    fun minX(): Long = start.x

    fun maxX(): Long = end.x

    fun minY(): Long = start.y

    fun maxY(): Long = end.y

    fun contains(y: Long): Boolean = y in start.y..end.y

    fun contains(point: Point): Boolean = point.x in start.x..end.x && point.y in start.y..end.y

    fun isVertical(): Boolean = start.x == end.x

    fun rotate(): LineSegment = LineSegment(Point(-end.y, start.x), Point(-start.y, end.x))
  }

  fun partOne(lines: List<String>): Long {
    val instructions =
      lines.map { line ->
        val (direction, amount, _) = line.split(" ")
        Instruction(direction.first(), amount.toInt())
      }
    return countDugSquares(generateLines(instructions))
  }

  fun partTwo(lines: List<String>): Long {
    val instructions =
      lines.map { line ->
        val (_, _, color) = line.split(" ")
        val hex = color.substringAfter("(#").substringBefore(")")
        Instruction(hex.last(), hex.take(5).toInt(16))
      }
    return countDugSquares(generateLines(instructions))
  }

  private fun generateLines(instructions: List<Instruction>): List<LineSegment> {
    return instructions
      .runningFold(Point(0, 0)) { point, instruction ->
        when (instruction.direction) {
          in setOf('3', 'U') -> point.copy(y = point.y - instruction.amount)
          in setOf('0', 'R') -> point.copy(x = point.x + instruction.amount)
          in setOf('1', 'D') -> point.copy(y = point.y + instruction.amount)
          else -> point.copy(x = point.x - instruction.amount)
        }
      }
      .zipWithNext()
      .map { points ->
        val (start, end) = points.toList().sortedWith(compareBy(Point::y).thenBy(Point::x))
        LineSegment(start, end)
      }
      .sortedBy(LineSegment::minX)
  }

  private tailrec fun countDugSquares(
    lines: List<LineSegment>,
    y: Long = lines.minOf(LineSegment::minY),
    sum: Long = 0
  ): Long {
    if (y > lines.maxOf(LineSegment::maxY)) return sum

    val row = lines.filter { it.contains(y) }
    val (vertical, horizontal) = row.partition(LineSegment::isVertical)

    val count =
      vertical
        .zipWithNext()
        .fold(0L to false) { (count, inside), (line, next) ->
          val hasSegmentBetween =
            horizontal.any {
              (it.contains(line.start) || it.contains(line.end)) && (it.contains(next.start) || it.contains(next.end))
            }
          val isJoint = (line.minY() == next.maxY() || line.maxY() == next.minY()) && hasSegmentBetween
          val isEdge = (line.minY() == next.minY() || line.maxY() == next.maxY()) && hasSegmentBetween

          when {
            isJoint -> count + next.maxX() - line.minX() to inside
            isEdge || !inside -> count + next.maxX() - line.minX() to !inside
            else -> count + 1 to false
          }
        }
        .first + 1

    val advance = countSkippableRows(lines, row, y)
    return countDugSquares(lines, y + advance, sum + count * advance)
  }

  private fun countSkippableRows(lines: List<LineSegment>, row: List<LineSegment>, y: Long): Long {
    return when {
      row.all(LineSegment::isVertical) -> {
        val nextHorizontalY = lines.filterNot { it.isVertical() || it.minY() < y }.minOf(LineSegment::minY)
        min(row.minOf(LineSegment::maxY), nextHorizontalY) - y
      }
      else -> 1
    }
  }
}
