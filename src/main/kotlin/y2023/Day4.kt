package y2023

import kotlin.math.pow

object Day4 {
  private data class Card(val id: Int, val winners: Set<Int>)

  fun partOne(lines: List<String>): Int = lines.map(::parse).sumOf { card -> 2.0.pow(card.winners.size - 1).toInt() }

  fun partTwo(lines: List<String>): Int {
    val cards = lines.map(::parse)

    return cards
      .fold(cards.associate { it.id to 1 }) { wins, card ->
        val winners = (1..card.winners.size).map(card.id::plus).toSet()
        wins + winners.associateWith { winner -> wins.getValue(winner) + wins.getValue(card.id) }
      }
      .values
      .sum()
  }

  private fun parse(line: String): Card {
    val id = line.substringAfter("Card ").substringBefore(": ").trim().toInt()
    val winners = line.substringAfter(": ").substringBefore(" | ").splitNumbers()
    val numbers = line.substringAfter(" | ").splitNumbers()
    return Card(id, numbers.filter(winners::contains).toSet())
  }

  private fun String.splitNumbers(): List<Int> = this.trim().split("\\s+".toRegex()).map(String::toInt)
}
