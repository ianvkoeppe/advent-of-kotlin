package y2021

import java.lang.Character.getNumericValue

object Day9 {

  private val adjacentSquares = listOf((1 to 0), (0 to 1), (-1 to 0), (0 to -1))

  fun partOne(lines: List<String>): Int {
    return lines
      .mapIndexed { i, line ->
        line
          .filterIndexed { j, char ->
            findAdjacentSquares(lines, i, j).all { (x, y) -> lines[x][y].code > char.code }
          }
          .map { char -> getNumericValue(char.code) + 1 }
          .sum()
      }
      .sum()
  }

  fun partTwo(lines: List<String>, covered: MutableSet<Pair<Int, Int>> = mutableSetOf()): Int {
    return lines
      .flatMapIndexed { i, line -> line.mapIndexed { j, _ -> findBasinSize(lines, i, j, covered) } }
      .sorted()
      .takeLast(3)
      .reduce(Int::times)
  }

  private fun findBasinSize(
    lines: List<String>,
    i: Int,
    j: Int,
    covered: MutableSet<Pair<Int, Int>>
  ): Int {
    return if (getNumericValue(lines[i][j]) == 9 || !covered.add((i to j))) 0
    else
      findAdjacentSquares(lines, i, j).sumOf { (x, y) -> findBasinSize(lines, x, y, covered) } + 1
  }

  private fun findAdjacentSquares(lines: List<String>, i: Int, j: Int): List<Pair<Int, Int>> {
    return adjacentSquares
      .filter { (x, y) ->
        i + x >= 0 && i + x < lines.size && j + y >= 0 && j + y < lines.first().length
      }
      .map { (x, y) -> i + x to y + j }
  }
}
