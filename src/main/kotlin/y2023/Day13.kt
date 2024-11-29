package y2023

object Day13 {
  private data class Valley(val lines: List<String>) {
    private val terrains: List<Terrain> =
      lines.joinToString("\n").split("\n\n").map { terrain -> terrain.split("\n").map(String::toList) }.map(::Terrain)

    fun findSumOfReflectionPoints(mismatches: Int = 0): Int =
      terrains.map { it.findReflectionPoint(mismatches) }.sumOf(ReflectionPoint::value)
  }

  private data class Terrain(val rocks: List<List<Char>>) {
    fun findReflectionPoint(mismatches: Int): ReflectionPoint {
      val x =
        findReflectionPoint(rocks.first().size, rocks.size, mismatches) { candidate, check -> rocks[check][candidate] }
          ?.let { ReflectionPoint('x', it + 1) }
      val y =
        findReflectionPoint(rocks.size, rocks.first().size, mismatches) { candidate, check -> rocks[candidate][check] }
          ?.let { ReflectionPoint('y', it + 1) }
      return listOfNotNull(x, y).first()
    }

    private fun findReflectionPoint(
      candidateSize: Int,
      checkSize: Int,
      mismatches: Int,
      comparison: (Int, Int) -> Char,
    ): Int? =
      (0 until candidateSize - 1).firstOrNull { candidate ->
        val matches =
          (0..kotlin.math.min(candidate, candidateSize - candidate - 2)).sumOf { iteration ->
            (0 until checkSize).count { check ->
              comparison(candidate - iteration, check) != comparison(candidate + iteration + 1, check)
            }
          }
        matches == mismatches
      }
  }

  private data class ReflectionPoint(val axis: Char, val coordinate: Int) {
    fun value(): Int = coordinate * (if (axis == 'y') 100 else 1)
  }

  fun partOne(lines: List<String>): Int = Valley(lines).findSumOfReflectionPoints()

  fun partTwo(lines: List<String>): Int = Valley(lines).findSumOfReflectionPoints(mismatches = 1)
}
