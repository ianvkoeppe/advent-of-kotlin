package y2022

object Day16 {
  data class Valve(val name: String, val flowRate: Int, val tunnels: Map<String, Int>)

  data class Position(val name: String = "Ian", val timeToErupt: Int = 30, val valve: String = "AA")

  data class Movement(val from: Position, val to: String, val timeToMoveToAndOpen: Int, val release: Int) {
    fun distinctKey(): String = "${from.timeToErupt}: ${from.valve}=>${to}"
  }

  data class Volcano(
    val tunnels: Map<String, Valve>,
    val positions: List<Position> = listOf(Position()),
    val opened: Set<String> = setOf(),
    val released: Int = 0
  ) {
    fun underPressureDoDoDoDo(): Int =
      findNextValves().maxOfOrNull { next -> moveToAndOpen(next).underPressureDoDoDoDo() } ?: released

    private fun findNextValves(): List<List<Movement>> {
      val options =
        positions.map { position ->
          val valve = tunnels.getValue(position.valve)
          valve.tunnels
            .filterNot { opened.contains(it.key) }
            .filter { (name, timeToMoveToAndOpen) ->
              tunnels.getValue(name).flowRate > 0 && position.timeToErupt - timeToMoveToAndOpen > 0
            }
            .map { (name, timeToMoveToAndOpen) ->
              Movement(
                position,
                name,
                timeToMoveToAndOpen,
                (position.timeToErupt - timeToMoveToAndOpen) * tunnels.getValue(name).flowRate
              )
            }
        }
      return findCartesianProductOfMoves(options)
    }

    private fun findCartesianProductOfMoves(movements: List<List<Movement>>): List<List<Movement>> {
      return movements
        .fold(listOf(listOf<Movement>())) { products, nexts ->
          products.flatMap { product -> nexts.map { next -> product + next } }
        }
        .filter { m -> haveDifferentDestinations(m) }
        .distinctBy { m -> m.map { it.distinctKey() }.sorted().joinToString(",") }
    }

    private fun haveDifferentDestinations(movements: List<Movement>): Boolean =
      movements.distinctBy { it.to }.count() == movements.count()

    private fun moveToAndOpen(movements: Collection<Movement>): Volcano {
      return copy(
        positions =
          positions.map { position ->
            movements
              .find { it.from == position }
              ?.let { movement ->
                position.copy(valve = movement.to, timeToErupt = position.timeToErupt - movement.timeToMoveToAndOpen)
              }
              ?: position
          },
        opened = opened + movements.map { it.to },
        released = released + movements.sumOf { it.release }
      )
    }
  }

  fun partOne(lines: List<String>): Int = parse(lines).underPressureDoDoDoDo()

  fun partTwo(lines: List<String>): Int =
    parse(lines).copy(positions = listOf(Position("Ian", 26), Position("Elephant", 26))).underPressureDoDoDoDo()

  private val input = "Valve ([A-Z]+) has flow rate=(\\d+); tunnels? leads? to valves? (.+)".toRegex()

  private fun parse(lines: List<String>): Volcano {
    return findShortestPaths(
      lines
        .map { line -> input.find(line)!!.groupValues }
        .associate { (_, id, rate, valves) -> (id to Valve(id, rate.toInt(), valves.split(", ").associateWith { 2 })) }
    )
  }

  private fun findShortestPaths(valves: Map<String, Valve>): Volcano {
    return Volcano(
      valves.mapValues { (_, valve) -> valve.copy(tunnels = findShortestPath(valves, valve.tunnels.keys.toList())) }
    )
  }

  private fun findShortestPath(
    valves: Map<String, Valve>,
    current: List<String>,
    visited: Set<String> = setOf(),
    timeToMoveToAndOpen: Int = 2
  ): Map<String, Int> {
    if (current.isEmpty()) return mapOf()
    val next = current.flatMap { valves.getValue(it).tunnels.keys }.filterNot(visited::contains)
    return findShortestPath(valves, next, visited + current, timeToMoveToAndOpen + 1) +
      current.associateWith { timeToMoveToAndOpen }
  }
}
