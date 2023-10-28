package y2022

import java.util.*
import kotlin.math.abs

object Day12 {
  private val adjacentSquares = listOf((1 to 0), (0 to 1), (-1 to 0), (0 to -1))
  private val squareEffectiveHeight = mapOf('S' to 'a', 'E' to 'z')

  fun partOne(lines: List<String>): Int = findShortestPathFrom(lines, setOf('S'))
  fun partTwo(lines: List<String>): Int = findShortestPathFrom(lines, setOf('S', 'a'))

  private fun findShortestPathFrom(lines: List<String>, starts: Set<Char>, end: Char = 'E'): Int {
    val hill = lines.map { it.toList() }
    val shortestPaths = findShortestPaths(hill, findCoordinatesOf(hill, setOf(end)).first())
    return findCoordinatesOf(hill, starts).filter(shortestPaths::containsKey).minOf(shortestPaths::getValue)
  }

  private fun findCoordinatesOf(hill: List<List<Char>>, targets: Set<Char>): List<Pair<Int, Int>> {
    return hill.flatMapIndexed { x, chars -> chars.mapIndexed { y, c -> if (c in targets) x to y else null }.filterNotNull() }
  }

  private tailrec fun findShortestPaths(
    hill: List<List<Char>>,
    current: Pair<Int, Int>,
    shortestPaths: MutableMap<Pair<Int, Int>, Int> = mutableMapOf(current to 0),
    unvisited: PriorityQueue<Pair<Int, Int>> = PriorityQueue<Pair<Int, Int>>(Comparator.comparingInt { n -> shortestPaths.getValue(n) })): Map<Pair<Int, Int>, Int> {
    val newShortestPaths = adjacentSquares.asSequence()
      .map { (adjX, adjY) -> Pair(current.first + adjX, current.second + adjY) }
      .filter { position -> isValidPosition(hill, current, position) }
      .map { adjacent -> adjacent to shortestPaths.getValue(current) + 1 }
      .filter { (adjacent, _) -> !shortestPaths.containsKey(adjacent) }
      .toMap()
    shortestPaths.putAll(newShortestPaths)
    unvisited.addAll(newShortestPaths.keys)
    return if (unvisited.isNotEmpty()) findShortestPaths(hill, unvisited.remove(), shortestPaths, unvisited) else shortestPaths
  }

  private fun isValidPosition(hill: List<List<Char>>, current: Pair<Int, Int>, next: Pair<Int, Int>): Boolean {
    val currentHeight = effectiveHeight(hill, current)
    val nextHeight = effectiveHeight(hill, next)
    return currentHeight != null && nextHeight != null && (abs(currentHeight - nextHeight) <= 1 || nextHeight > currentHeight)
  }

  private fun effectiveHeight(hill: List<List<Char>>, position: Pair<Int, Int>): Char? {
    val value = hill.getOrNull(position.first)?.getOrNull(position.second)
    return squareEffectiveHeight[value] ?: value
  }
}
