package common

data class Point(val x: Int, val y: Int, val z: Int = 0) {
  fun adjacentPoints(): Set<Point> = setOf(up(), right(), down(), left())

  private fun up(): Point = copy(y = y - 1)

  private fun right(): Point = copy(x = x + 1)

  private fun down(): Point = copy(y = y + 1)

  private fun left(): Point = copy(x = x - 1)
}
