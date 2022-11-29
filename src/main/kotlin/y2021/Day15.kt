package y2021

import java.util.*

data class Node(val x: Int, val y: Int)

object Day15 {

  private val adjacentSquares = listOf((1 to 0), (0 to 1), (-1 to 0), (0 to -1))

  fun partOne(lines: List<String>): Int {
    val risks = lines.map { it.map(Character::getNumericValue) }
    return findRiskOfSafestPath(risks)
  }

  fun partTwo(lines: List<String>): Int {
    val risks = lines.map { it.map(Character::getNumericValue) }
    return findRiskOfSafestPath(risks, 5)
  }

  private fun findRiskOfSafestPath(risks: List<List<Int>>, mapMultiplier: Int = 1): Int {
    val shortestPaths = mutableMapOf(Node(0, 0) to 0)
    val unvisited = PriorityQueue<Node>(Comparator.comparingInt { n -> shortestPaths.getValue(n) })
    unvisited.add(Node(0, 0))
    val visited: MutableSet<Node> = mutableSetOf()

    while (unvisited.isNotEmpty()) {
      val current = unvisited.remove()
      findAdjacentSquares(risks, current, mapMultiplier)
        .filterNot(visited::contains)
        .forEach { adjacent ->
          val candidate = shortestPaths.getValue(current) + calculateRisk(risks, adjacent)
          if (!shortestPaths.containsKey(adjacent) || candidate < shortestPaths.getValue(adjacent)) {
            shortestPaths[adjacent] = candidate
            unvisited.add(adjacent)
          }
      }
      visited.add(current)
    }

    return shortestPaths.getValue(Node(risks.size * mapMultiplier - 1, risks.size * mapMultiplier - 1))
  }

  private fun findAdjacentSquares(risks: List<List<Int>>, node: Node, mapMultiplier: Int): List<Node> {
    return adjacentSquares
      .filter { (i, _) -> node.x + i >= 0 && node.x + i < risks.size * mapMultiplier }
      .filter { (_, j) -> node.y + j >= 0 && node.y + j < risks.size * mapMultiplier }
      .map { (i, j) -> Node(node.x + i,  node.y + j) }
  }

  private fun calculateRisk(risks: List<List<Int>>, node: Node): Int {
    val risk = risks[node.x % risks.size][node.y % risks.size] + (node.x / risks.size) + (node.y / risks.size)
    return if (risk >= 10) risk % 9 else risk
  }
}