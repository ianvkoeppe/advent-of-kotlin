package days

data class Point(val x: Int, val y: Int)
data class Line(val start: Point, val end: Point) {
  fun isHorizontalOrVeritcal(): Boolean {
    return start.x == end.x || start.y == end.y
  }

  fun getPoints(): List<Point> {
    val slope = if (end.x - start.x != 0) (end.y - start.y) / (end.x - start.x) else null
    val yIntercept = if (slope != null) start.y - (slope * start.x) else null

    val (minX, maxX) = if (start.x < end.x || (start.x == end.x && start.y < end.y)) Pair(start, end) else Pair(end, start)
    return generateSequence(minX) { point ->
      val (newX, newY) = if (slope != null && yIntercept != null) Pair(point.x + 1, slope * (point.x + 1) + yIntercept) else Pair(point.x, point.y + 1)
      Point(newX, newY)
    }.takeWhile { it != maxX }.toList() + maxX
  }
}

object Day5 {
  fun partOne(lines: List<String>): Int {
    return countIntersections(parse(lines).filter(Line::isHorizontalOrVeritcal))
  }

  fun partTwo(lines: List<String>): Int {
    return countIntersections(parse(lines))
  }

  private fun parse(lines: List<String>): List<Line> {
    return lines.map { line ->
      val points = line.split(" -> ").map { point ->
        val nums = point.split(",").map { it.toInt() }
        Point(nums[0], nums[1])
      }
      Line(points[0], points[1])
    }
  }

  private fun countIntersections(lines: List<Line>): Int {
    val counts = mutableMapOf<Point, Int>()
    lines.forEach { line ->
      line.getPoints().forEach { point -> counts.merge(point, 1, Int::plus) }
    }
    return counts.values.count { it >= 2 }
  }
}
