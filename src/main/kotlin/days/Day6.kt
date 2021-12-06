package days

import kotlin.math.max
import kotlin.math.min

object Day6 {

  private const val maxFishTime: Long = 8

  object DynamicProgrammingApproach {
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

    fun countDescendents(fish: Int, days: Int, counts: MutableMap<Pair<Int, Int>, Long> = mutableMapOf()): Long {
      return counts.computeIfAbsent((fish to days)) { _ ->
        val descendents = max(min(days - fish, 1), 0) + (days - fish - 1) / 7
        (0 until descendents).sumOf { countDescendents(8, days - fish - 1 - (it * 7), counts) }
      } + 1
    }
  }

  object ReduceThenSimulateApproach {
    fun partOne(lines: List<String>): Long {
      return simulate(parse(lines), 80)
    }

    fun partTwo(lines: List<String>): Long {
      return simulate(parse(lines), 256)
    }

    private fun simulate(fish: List<Long>, days: Int): Long {
      return (0 until days).fold(fish) { f, _ ->
        f.indices.map { index -> f[(index + 1) % f.size] + if (index == 6) f[0] else 0 }
      }.sum()
    }
  }

  object HybridApproach {
    fun partOne(lines: List<String>): Long {
      return countDescendents(parse(lines), 80)
    }

    fun partTwo(lines: List<String>): Long {
      return countDescendents(parse(lines), 256)
    }

    private fun countDescendents(fish: List<Long>, days: Int): Long {
      return fish.indices.sumOf { DynamicProgrammingApproach.countDescendents(it, days) * fish[it] }
    }
  }

  private fun parse(lines: List<String>): List<Long> {
    val fishCountByDay = lines.first().split(",").map { it.toLong() }.groupingBy { it }.eachCount()
    return (0..maxFishTime).map { fishCountByDay.getOrDefault(it, 0).toLong() }
  }
}
