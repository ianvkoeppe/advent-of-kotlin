package y2023

object Day25 {
  private data class Graph(val graph: Map<String, Set<String>>) {
    fun findProductOfMinCutPartitions(): Int {
      val vertices = graph.keys.toMutableSet()

      while (vertices.sumOf { (graph.getValue(it) - vertices).size } != 3) {
        vertices.remove(vertices.maxBy { (graph.getValue(it) - vertices).size })
      }

      return vertices.size * (graph.size - vertices.size)
    }
  }

  fun partOne(lines: List<String>): Int = Graph(parse(lines)).findProductOfMinCutPartitions()

  private fun parse(lines: List<String>): Map<String, Set<String>> {
    return lines
      .flatMap { line ->
        val (name, connections) = line.split(": ")
        connections.split(" ").flatMap { connection -> setOf(name to connection, connection to name) }
      }
      .groupBy(Pair<String, String>::first)
      .mapValues { it.value.map(Pair<String, String>::second).toSet() }
  }
}
