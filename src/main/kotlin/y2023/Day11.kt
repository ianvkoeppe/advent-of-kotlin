package y2023

import kotlin.math.*

object Day11 {
  data class Universe(private val lines: List<String>) {
    private val map: List<List<Char>> = lines.map(String::toList)
    private val galaxies: List<Pair<Int, Int>> =
      map.indices.flatMap { y -> map[y].indices.map { it to y } }.filter { (x, y) -> map[y][x] == '#' }
    private val emptyRows: Set<Int> = map.indices.filter { map[it].all('.'::equals) }.toSet()
    private val emptyCols: Set<Int> = map.indices.filter { y -> map[y].indices.all { map[it][y] == '.' } }.toSet()

    fun findSumOfShortestDistances(expansion: Int = 2): Long {
      val pairs = galaxies.flatMapIndexed { index, galaxy -> galaxies.drop(index + 1).map { galaxy to it } }

      return pairs.sumOf { (f, s) ->
        val distance = abs(s.second - f.second) + abs(s.first - f.first).toLong()
        val emptyRowCount = (min(s.second, f.second)..max(s.second, f.second)).count(emptyRows::contains)
        val emptyColCount = (min(s.first, f.first)..max(s.first, f.first)).count(emptyCols::contains)
        distance + (expansion * emptyRowCount - emptyRowCount) + (expansion * emptyColCount - emptyColCount)
      }
    }
  }

  fun partOne(lines: List<String>): Long = Universe(lines).findSumOfShortestDistances()

  fun partTwo(lines: List<String>, expansion: Int = 1): Long = Universe(lines).findSumOfShortestDistances(expansion)
}
