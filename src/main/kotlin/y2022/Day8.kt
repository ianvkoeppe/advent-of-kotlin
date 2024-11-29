package y2022

object Day8 {
  private data class Visibility<T>(val up: T, val right: T, val down: T, val left: T)

  fun partOne(lines: List<String>): Int {
    return countWithScore(lines) { current, neighbors -> neighbors.all { it < current } }
      .sumOf { row -> row.count { it.up || it.right || it.down || it.left } }
  }

  fun partTwo(lines: List<String>): Int {
    return countWithScore(lines) { current, neighbors ->
        if (neighbors.isEmpty()) 0
        else {
          val shorter = neighbors.takeWhile { neighbor -> neighbor < current }.count()
          val maybeTaller = neighbors.drop(shorter).firstOrNull() ?: -1
          shorter + if (maybeTaller >= current) 1 else 0
        }
      }
      .maxOf { row -> row.maxOf { it.up * it.right * it.down * it.left } }
  }

  private fun <T> countWithScore(
    lines: List<String>,
    visibilityScorer: (Int, List<Int>) -> T,
  ): List<List<Visibility<T>>> {
    val trees = lines.map { it.map(Char::digitToInt) }
    return List(trees.size) { currentX ->
      List(trees.size) { currentY ->
        val up = score(trees, currentX, currentY, visibilityScorer) { x, y -> Pair(x, y + 1) }
        val right = score(trees, currentX, currentY, visibilityScorer) { x, y -> Pair(x + 1, y) }
        val down = score(trees, currentX, currentY, visibilityScorer) { x, y -> Pair(x, y - 1) }
        val left = score(trees, currentX, currentY, visibilityScorer) { x, y -> Pair(x - 1, y) }
        Visibility(up, right, down, left)
      }
    }
  }

  private fun <T> score(
    trees: List<List<Int>>,
    x: Int,
    y: Int,
    visibilityScorer: (Int, List<Int>) -> T,
    nextIndices: (Int, Int) -> Pair<Int, Int>,
  ): T {
    val neighbors =
      generateSequence(Pair(x, y)) { (x, y) -> nextIndices(x, y) }
        .takeWhile { (x, y) -> isValidIndex(trees, x, y) }
        .map { trees[it.first][it.second] }
        .drop(1)
        .toList()
    return visibilityScorer(trees[x][y], neighbors)
  }

  private fun isValidIndex(trees: List<List<Int>>, x: Int, y: Int): Boolean =
    x >= 0 && x < trees.size && y >= 0 && y < trees[x].size
}
