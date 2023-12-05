package y2023

import kotlin.math.pow

object Day4 {
  fun partOne(lines: List<String>): Int = lines.map(::parse).sumOf { winners -> 2.0.pow(winners - 1).toInt() }

  fun partTwo(lines: List<String>): Int {
    val cards = lines.map(::parse)

    return cards
      .foldIndexed(cards.withIndex().associate { it.index to 1 }) { index, wins, winners ->
        wins + (1..winners).map(index::plus).associateWith { winner -> wins.getValue(winner) + wins.getValue(index) }
      }
      .values
      .sum()
  }

  private fun parse(line: String): Int {
    val winners = line.substringAfter(": ").substringBefore(" | ").splitNumbers()
    val numbers = line.substringAfter(" | ").splitNumbers()
    return numbers.count(winners::contains)
  }

  private fun String.splitNumbers(): List<Int> = this.trim().split("\\s+".toRegex()).map(String::toInt)
}
