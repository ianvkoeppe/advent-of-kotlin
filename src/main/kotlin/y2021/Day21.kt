package y2021

object Day21 {
  private data class Player(val n: Int, val initialPosition: Int)

  private data class GameState(val positions: Map<Int, Int>, val scores: Map<Int, Int>, val turn: Int)

  private val diracRolls: Map<Int, Int> =
    (1..3)
      .flatMap { first -> (1..3).flatMap { second -> (1..3).map { third -> first + second + third } } }
      .groupingBy { it }
      .eachCount()

  fun partOne(lines: List<String>): Int {
    val result = playUntil(parse(lines), 1000)
    return (result.scores.values.minOrNull() ?: 0) * result.turn * 3
  }

  fun partTwo(lines: List<String>): Long {
    return countUniverses(parse(lines), 21).maxOf { it.value }
  }

  private fun parse(lines: List<String>): List<Player> {
    return lines.map { line ->
      val n = line.removePrefix("Player ").take(1).toInt()
      val position = line.removePrefix("Player $n starting position: ").toInt()
      Player(n, position)
    }
  }

  private fun playUntil(
    players: List<Player>,
    winningScore: Int,
    state: GameState = GameState(players.associate { it.n to it.initialPosition }, mapOf(), 0),
  ): GameState {
    if (state.scores.values.any { it >= winningScore }) return state
    return playUntil(players.drop(1) + players.first(), winningScore, turn(players.first(), state))
  }

  private fun countUniverses(
    players: List<Player>,
    winningScore: Int,
    state: GameState = GameState(players.associate { it.n to it.initialPosition }, mapOf(), 0),
  ): Map<Int, Long> {
    if (state.scores.values.any { it >= winningScore }) return mapOf(state.scores.maxByOrNull { it.value }!!.key to 1)

    return diracRolls
      .map { (roll, count) ->
        countUniverses(players.drop(1) + players.first(), winningScore, turn(players.first(), state, roll)).mapValues {
          (_, v) ->
          count * v
        }
      }
      .reduce { a, b -> (a.keys + b.keys).associateWith { (a[it] ?: 0) + (b[it] ?: 0) } }
  }

  private fun turn(player: Player, state: GameState): GameState {
    return turn(player, state, 6 + (state.turn * 9))
  }

  private fun turn(player: Player, state: GameState, roll: Int): GameState {
    val afterRoll = roll + state.positions.getValue(player.n)
    val position = if (afterRoll % 10 == 0) 10 else if (afterRoll > 10) afterRoll % 10 else afterRoll
    val updatedScore = state.scores + (player.n to state.scores.getOrDefault(player.n, 0) + position)
    return state.copy(
      positions = state.positions + (player.n to position),
      scores = updatedScore,
      turn = state.turn + 1,
    )
  }
}
