package y2022

object Day1 {
  fun partOne(lines: List<String>): Int? {
    return findElfCalories(lines).maxOrNull()
  }

  fun partTwo(lines: List<String>): Int {
    return findElfCalories(lines).sortedDescending().take(3).sum()
  }

  private fun findElfCalories(lines: List<String>): List<Int> {
    return lines.joinToString("\n").split("\n\n").map { it.split("\n").sumOf { it.toInt() } }
  }
}
