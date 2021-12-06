package days

object Day6 {

  private const val maxFishTime: Long = 8

  object DynamicProgramming {
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

  object ReduceThenSimulate {
    fun partOne(lines: List<String>): Long {
      return simulate(parse(lines), 80)
    }

    fun partTwo(lines: List<String>): Long {
      return simulate(parse(lines), 256)
    }

    private fun simulate(fish: List<Long>, days: Int): Long {
      if (days == 0) return fish.sum()
      return simulate(simulate(fish.toMutableList()), days - 1)
    }

    private fun simulate(fish: List<Long>): List<Long> {
      return fish.indices.map { index ->
        fish[(index + 1) % fish.size] + if (index == 6) fish[0] else 0
      }
    }
  }

  private fun parse(lines: List<String>): List<Long> {
    val fishCountByDay = lines.first().split(",").map { it.toLong() }.groupingBy { it }.eachCount()
    return (0..maxFishTime).map { fishCountByDay.getOrDefault(it, 0).toLong() }
  }
}
