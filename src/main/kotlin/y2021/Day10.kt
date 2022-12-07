package y2021

object Day10 {

  private val closeToOpen = mapOf((')' to '('), (']' to '['), ('}' to '{'), ('>' to '<'))
  private val openToClose = closeToOpen.map { (k, v) -> (v to k) }.toMap()
  private val costs = mapOf((')' to 3L), (']' to 57L), ('}' to 1197L), ('>' to 25137L))
  private val weights = mapOf((')' to 1L), (']' to 2L), ('}' to 3L), ('>' to 4L))

  fun partOne(lines: List<String>): Long {
    return lines.sumOf { line ->
      val (syntaxError, _) = findSyntaxError(line)
      costs.getOrDefault(syntaxError, 0L)
    }
  }

  fun partTwo(lines: List<String>): Long {
    val costs = lines.map { findSyntaxError(it) }.filter { it.first == null }.map { (_, remainingOpenChars) ->
      remainingOpenChars.foldRight(0L) { char, total ->
        total * 5 + weights.getValue(openToClose.getValue(char))
      }
    }.sorted()
    return costs[costs.size / 2]
  }

  private fun findSyntaxError(line: String): Pair<Char?, ArrayDeque<Char>> {
    val remainingOpenChars = ArrayDeque<Char>()
    val syntaxError = line.dropWhile { char ->
      if (openToClose.containsKey(char)) remainingOpenChars.add(char) else remainingOpenChars.removeLast() == closeToOpen[char]
    }.firstOrNull()
    return syntaxError to remainingOpenChars
  }
}
