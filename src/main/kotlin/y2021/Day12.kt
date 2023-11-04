package y2021

object Day12 {

  fun partOne(lines: List<String>): Int {
    return countPaths(parse(lines), 1, "start")
  }

  fun partTwo(lines: List<String>): Int {
    return countPaths(parse(lines), 2, "start")
  }

  private fun parse(lines: List<String>): Map<String, List<String>> {
    return lines
      .map { it.split("-") }
      .flatMap { (a, b) -> listOf((a to b), (b to a)) }
      .groupBy({ it.first }, { it.second })
  }

  private fun countPaths(
    paths: Map<String, List<String>>,
    maxSingleSmallCaveVisits: Int,
    current: String,
    previouslyVisitedCaves: Map<String, Int> = mapOf(),
    acc: MutableMap<Pair<String, String>, Int> = mutableMapOf()
  ): Int {
    if (current == "end") return 1

    val visited =
      if (current.lowercase() == current)
        previouslyVisitedCaves + (current to (previouslyVisitedCaves[current] ?: 0) + 1)
      else previouslyVisitedCaves
    return paths
      .getValue(current)
      .filter { cave ->
        cave != "start" &&
          (cave.uppercase() == cave || (visited[cave] ?: 0) < 1 || !visited.containsValue(maxSingleSmallCaveVisits))
      }
      .sumOf { cave ->
        val visitedKey = visited.entries.fold("") { str, (k, v) -> str + k.repeat(v) }
        acc.getOrPut(cave to visitedKey) { countPaths(paths, maxSingleSmallCaveVisits, cave, visited, acc) }
      }
  }
}
