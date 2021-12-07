package days

object Day6 {

  private const val maxFishTime: Long = 8

  fun partOne(lines: List<String>): Long {
    return step(parse(lines), 80)
  }

  fun partTwo(lines: List<String>): Long {
    return step(parse(lines), 256)
  }

  private fun step(fish: List<Long>, days: Int): Long {
    return (0 until days).fold(fish) { f, _ ->
      f.indices.map { index -> f[(index + 1) % f.size] + if (index == 6) f[0] else 0 }
    }.sum()
  }

  private fun parse(lines: List<String>): List<Long> {
    val fishCountByDay = lines.first().split(",").map { it.toLong() }.groupingBy { it }.eachCount()
    return (0..maxFishTime).map { fishCountByDay.getOrDefault(it, 0).toLong() }
  }
}
