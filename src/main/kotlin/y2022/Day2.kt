package y2022

object Day2 {
  enum class Choice {
    ROCK,
    PAPER,
    SCISSORS,
  }

  private val choices =
    mapOf(
      'A' to Choice.ROCK,
      'X' to Choice.ROCK,
      'B' to Choice.PAPER,
      'Y' to Choice.PAPER,
      'C' to Choice.SCISSORS,
      'Z' to Choice.SCISSORS,
    )
  private val outcomes =
    mapOf(-1 to 6, 0 to 3, 1 to 0) // Take advantage of the idea that choice at index beats choice at index - 1, draws

  // at same index, and loses to index + 1.

  fun partOne(lines: List<String>): Int {
    return findScoreWithStrategy(lines)
  }

  fun partTwo(lines: List<String>): Int {
    return findScoreWithStrategy(lines) { theirMove, yourStrategy -> choiceByDesiredOutcome(theirMove, yourStrategy) }
  }

  private fun findScoreWithStrategy(
    lines: List<String>,
    strategy: (Choice, Char) -> Choice = { _, yourStrategy -> choices.getValue(yourStrategy) },
  ): Int {
    return lines
      .map { it.split(" ") }
      .map { (theirMove, yourStrategy) -> theirMove.first() to yourStrategy.first() }
      .map { (theirMove, yourStrategy) ->
        choices.getValue(theirMove) to strategy(choices.getValue(theirMove), yourStrategy)
      }
      .sumOf { (theirMove, yourMove) -> score(theirMove, yourMove) }
  }

  private fun choiceByDesiredOutcome(theirMove: Choice, desiredOutcome: Char): Choice =
    Choice.entries[(theirMove.ordinal + (desiredOutcome.code - 'Y'.code) + 3) % 3]

  private fun score(theirMove: Choice, yourMove: Choice): Int {
    return yourMove.ordinal +
      1 +
      outcomes
        .map { (indexOffset, score) -> if ((theirMove.ordinal - indexOffset) % 3 == yourMove.ordinal) score else 0 }
        .sum()
  }
}
