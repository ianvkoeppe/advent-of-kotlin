package y2021

object Day13 {
  data class Point(val x: Int, val y: Int)
  data class Fold(val axis: Char, val position: Int)

  fun partOne(lines: List<String>): Int {
    val (grid, folds) = parse(lines)
    return fold(grid, folds.first()).count()
  }

  fun partTwo(lines: List<String>): String {
    val (grid, folds) = parse(lines)
    return toString(folds.fold(grid) { g, fold -> fold(g, fold) })
  }

  private fun parse(lines: List<String>): Pair<Set<Point>, List<Fold>> {
    val points = lines.takeWhile { it != "" }.map { it.split(",") }.map { (x, y) -> Point(x.toInt(), y.toInt()) }.toSet()
    val folds = lines.takeLastWhile { it != "" }.map { it.split("=") }.map { (axis, position) -> Fold(axis.last(), position.toInt()) }
    return points to folds
  }

  private fun fold(points: Set<Point>, fold: Fold): Set<Point> {
    return points.map { (x, y) ->
      if (fold.axis == 'x' && x > fold.position) Point(fold.position - (x - fold.position), y)
      else if (fold.axis == 'y' && y > fold.position) Point(x, fold.position - (y - fold.position))
      else Point(x, y)
    }.toSet()
  }

  private fun toString(points: Set<Point>): String {
    return (0..points.maxOf { it.y }).joinToString("\n") { y ->
      (0..points.maxOf { it.x }).joinToString("") { x -> if (points.contains(Point(x, y))) "#" else " " }
    }
  }
}
