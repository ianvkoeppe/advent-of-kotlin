package y2022

object Day5 {
  data class Move(val amount: Int, val from: Int, val to: Int)

  fun partOne(lines: List<String>): String {
    return moveAndFindTopCrates(lines) { stacks, move ->
      repeat(move.amount) { stacks.getValue(move.to).addLast(stacks.getValue(move.from).removeLast()) }
    }
  }

  fun partTwo(lines: List<String>): String {
    return moveAndFindTopCrates(lines) { stacks, move ->
      (1..move.amount).map { stacks.getValue(move.from).removeLast() }.reversed().map { stacks.getValue(move.to).addLast(it) }
    }
  }

  private fun moveAndFindTopCrates(lines: List<String>, movement: (Map<Int, ArrayDeque<Char>>, Move) -> Unit): String =
    move(lines, movement).map { it.value.last() }.joinToString("")
  private fun move(lines: List<String>, movement: (Map<Int, ArrayDeque<Char>>, Move) -> Unit): Map<Int, ArrayDeque<Char>> {
    val (stacks, moves) = lines.joinToString("\n").split("\n\n").map { it.split("\n") }
    return parseMoves(moves).fold(parseStacks(stacks)) { s, m ->
      movement(s, m)
      s
    }
  }

  private fun parseStacks(lines: List<String>): Map<Int, ArrayDeque<Char>> {
    val chars = lines.dropLast(1).map { line -> line.map { it } }

    return (0 until chars.last().size).map { x -> (chars.size - 1 downTo 0).map { y -> if (y < chars.size && x < chars[y].size) chars[y][x] else ' ' } }
      .filter { it.first() in 'A'..'Z' }
      .mapIndexed { index, crates -> index + 1 to ArrayDeque(crates.filter { it != ' ' }.toList()) }
      .toMap()
  }

  private fun parseMoves(lines: List<String>): List<Move> {
    return lines.map { line ->
      val words = line.split(" ")
      Move(words[1].toInt(), words[3].toInt(), words[5].toInt())
    }
  }
}