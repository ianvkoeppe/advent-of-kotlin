package y2023

object Day8 {
  private data class DesertMap(val directions: List<Char>, val nodes: Map<String, Pair<String, String>>) {
    companion object {
      fun parse(lines: List<String>): DesertMap {
        val inputs = lines.joinToString("\n").split("\n\n")
        val nodes =
          inputs.last().split("\n").associate { node ->
            val (id, children) = node.split(" = ")
            val (left, right) = children.removePrefix("(").removeSuffix(")").split(", ")
            id to (left to right)
          }
        return DesertMap(inputs.first().toList(), nodes)
      }
    }

    fun findFewestStepsOfAllPaths(pathFilter: (String) -> Boolean): Long {
      val starts = nodes.keys.filter(pathFilter)
      return starts.map(::travel).reduce(::lcm)
    }

    private tailrec fun travel(location: String, currentDirection: Int = 0, steps: Long = 0): Long {
      if (location.last() == 'Z') return steps

      val (left, right) = nodes.getValue(location)
      val nextDirection = (currentDirection + 1) % directions.size
      return travel(if (directions[currentDirection] == 'L') left else right, nextDirection, steps + 1)
    }
  }

  fun partOne(lines: List<String>): Long = DesertMap.parse(lines).findFewestStepsOfAllPaths { it == "AAA" }

  fun partTwo(lines: List<String>): Long = DesertMap.parse(lines).findFewestStepsOfAllPaths { it.last() == 'A' }

  private fun lcm(a: Long, b: Long): Long = a * (b / gcd(a, b))

  private fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
}
