package days

object Day6 {
  fun partOne(lines: List<String>): Long {
    return simulate(parse(lines), 80)
  }

  fun partTwo(lines: List<String>): Long {
    return simulate(parse(lines), 256)
  }

  private fun parse(lines: List<String>): List<Long> {
    val fishCountByDay = lines.first().split(",").map { it.toLong() }.groupingBy { it }.eachCount()
    return (0 until 9L).map { fishCountByDay.getOrDefault(it, 0).toLong() }
  }

  private fun simulate(fish: List<Long>, days: Int): Long {
    if (days == 0) return fish.sum()
    return simulate(simulate(fish.toMutableList()), days - 1)
  }

  private fun simulate(fish: List<Long>): List<Long> {
    return fish.indices.map { index ->
      when (index) {
        6 -> fish[0] + fish[7]
        else -> fish[(index + 1) % fish.size]
      }
    }
  }
}
