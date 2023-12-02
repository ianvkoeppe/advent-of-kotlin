package y2023

object Day3 {
  private data class Part(val id: Int, val startX: Int, val y: Int)

  private val surroundingSquares: List<Pair<Int, Int>> =
    listOf(1 to 0, 1 to -1, 0 to -1, -1 to -1, -1 to 0, -1 to 1, 0 to 1, 1 to 1)

  fun partOne(lines: List<String>): Int = findPartsAroundEachSymbol(lines).flatten().sumOf(Part::id)

  fun partTwo(lines: List<String>): Int {
    val numbers = findPartsAroundEachSymbol(lines) { it == '*' }
    return numbers.filter { it.size == 2 }.sumOf { aroundSymbol -> aroundSymbol.map(Part::id).reduce(Int::times) }
  }

  private fun findPartsAroundEachSymbol(lines: List<String>, filter: (Char) -> Boolean = { true }): List<Set<Part>> {
    val schemata = lines.map(String::toList)
    val symbols = findLocationOfSymbols(schemata).filter { (x, y) -> filter(schemata[y][x]) }
    return symbols.map { (x, y) -> findSurroundingNumbers(schemata, x, y) }
  }

  private fun findLocationOfSymbols(schemata: List<List<Char>>): List<Pair<Int, Int>> {
    return schemata.flatMapIndexed { y, row ->
      row.mapIndexedNotNull { x, symbol -> if (!symbol.isDigit() && symbol != '.') x to y else null }
    }
  }

  private fun findSurroundingNumbers(schemata: List<List<Char>>, x: Int, y: Int): Set<Part> {
    return findSurroundingSquares(schemata, x, y)
      .filter { (x, y) -> schemata[y][x].isDigit() }
      .map { (x, y) -> parseNumber(schemata, x, y) }
      .toSet()
  }

  private fun findSurroundingSquares(schemata: List<List<Char>>, x: Int, y: Int): List<Pair<Int, Int>> {
    return surroundingSquares
      .map { (adjX, adjY) -> x + adjX to y + adjY }
      .filter { (x, y) -> x >= 0 && y >= 0 && y < schemata.size && x < schemata[y].size }
  }

  private fun parseNumber(schemata: List<List<Char>>, x: Int, y: Int): Part {
    val startX = (x downTo 0).takeWhile { schemata[y][it].isDigit() }.last()
    val value =
      (startX until schemata[y].size).map { schemata[y][it] }.takeWhile(Char::isDigit).joinToString("").toInt()
    return Part(value, startX, y)
  }
}
