package days

object Day6 {
  fun partOne(lines: List<String>): Long {
    val fish = lines.first().split(",").map { it.toInt() }
    return countDescendents(fish, 80)
  }

  fun partTwo(lines: List<String>): Long {
    val fish = lines.first().split(",").map { it.toInt() }
    return countDescendents(fish, 256)
  }

  private fun countDescendents(fish: List<Int>, days: Int): Long {
    return fish.sumOf { countDescendents(it, days) }
  }

  private fun countDescendents(fish: Int, days: Int, counts: MutableMap<Pair<Int, Int>, Long> = mutableMapOf()): Long {
    return counts.computeIfAbsent((fish to days)) { _ ->
      val firstBorn = if (days - fish >= 1) 1 else 0
      val descendents = firstBorn + (days - fish - 1) / 7
      (0 until descendents).sumOf { countDescendents(8, days - fish - 1 - (it * 7), counts) }
    } + 1
  }
}
