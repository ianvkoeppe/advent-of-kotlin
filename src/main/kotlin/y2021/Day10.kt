package y2021

object Day10 {

  private val CLOSE_TO_OPEN = mapOf((')' to '('), (']' to '['), ('}' to '{'), ('>' to '<'))
  private val OPEN_TO_CLOSE = CLOSE_TO_OPEN.map { (k, v) -> (v to k) }.toMap()
  private val COSTS = mapOf((')' to 3L), (']' to 57L), ('}' to 1197L), ('>' to 25137L))
  private val WEIGHTS = mapOf((')' to 1L), (']' to 2L), ('}' to 3L), ('>' to 4L))

  fun partOne(lines: List<String>): Long {
    return lines.sumOf { line ->
      val (syntaxError, _) = findSyntaxError(line)
      COSTS.getOrDefault(syntaxError, 0L)
    }
  }

  fun partTwo(lines: List<String>): Long {
    val costs = lines.map { findSyntaxError(it) }.filter { it.first == null }.map { (_, remainingOpenChars) ->
      remainingOpenChars.foldRight(0L) { char, total ->
        total * 5 + WEIGHTS.getValue(OPEN_TO_CLOSE.getValue(char))
      }
    }.sorted()
    return costs[costs.size / 2]
  }

  private fun findSyntaxError(line: String): Pair<Char?, ArrayDeque<Char>> {
    val remainingOpenChars = ArrayDeque<Char>()
    val syntaxError = line.dropWhile { char ->
      if (OPEN_TO_CLOSE.containsKey(char)) remainingOpenChars.add(char) else remainingOpenChars.removeLast() == CLOSE_TO_OPEN[char]
    }.firstOrNull()
    return syntaxError to remainingOpenChars
  }
}
