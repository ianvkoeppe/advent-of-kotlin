package y2023

import kotlin.math.max

object Day2 {
  private data class Game(val id: Int, val turns: Set<Map<String, Int>>)

  private val availableCubes: Map<String, Int> = mapOf("red" to 12, "green" to 13, "blue" to 14)

  fun partOne(lines: List<String>): Int {
    return lines
      .map(::parse)
      .filter { game ->
        game.turns.all { turn -> turn.all { (color, amount) -> (availableCubes[color] ?: 0) >= amount } }
      }
      .sumOf(Game::id)
  }

  fun partTwo(lines: List<String>): Int {
    return lines
      .map(::parse)
      .map { game ->
        game.turns.reduce { maxByColor, turn ->
          maxByColor + turn.map { (color, amount) -> color to max(maxByColor[color] ?: 0, amount) }
        }
      }
      .sumOf { it.values.reduce(Int::times) }
  }

  private fun parse(line: String): Game {
    val id = line.substringAfter("Game ").substringBefore(":").toInt()
    val turns =
      line
        .substringAfter(": ")
        .split("; ")
        .map { turn ->
          turn.split(", ").associate {
            val (amount, color) = it.split(" ")
            color to amount.toInt()
          }
        }
        .toSet()
    return Game(id, turns)
  }
}
