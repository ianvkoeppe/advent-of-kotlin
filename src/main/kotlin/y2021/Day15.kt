package y2021

import java.util.*

object Day15 {
  data class Node(val x: Int, val y: Int)

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

    while (unvisited.isNotEmpty()) {
      val current = unvisited.remove()
      findAdjacentSquares(risks, current, mapMultiplier)
        .map { adjacent ->
          adjacent to shortestPaths.getValue(current) + calculateRisk(risks, adjacent)
        }
        .filter { (adjacent, _) -> !shortestPaths.containsKey(adjacent) }
        .forEach { (adjacent, candidate) ->
          shortestPaths[adjacent] = candidate
          unvisited.add(adjacent)
        }
    }

    return shortestPaths.getValue(
      Node(risks.size * mapMultiplier - 1, risks.size * mapMultiplier - 1)
    )
  }

  private fun findAdjacentSquares(
    risks: List<List<Int>>,
    node: Node,
    mapMultiplier: Int
  ): List<Node> {
    return adjacentSquares
      .filter { (i, _) -> node.x + i >= 0 && node.x + i < risks.size * mapMultiplier }
      .filter { (_, j) -> node.y + j >= 0 && node.y + j < risks.size * mapMultiplier }
      .map { (i, j) -> Node(node.x + i, node.y + j) }
  }

  private fun calculateRisk(risks: List<List<Int>>, node: Node): Int {
    val risk =
      risks[node.x % risks.size][node.y % risks.size] +
        (node.x / risks.size) +
        (node.y / risks.size)
    return if (risk >= 10) risk % 9 else risk
  }
}
