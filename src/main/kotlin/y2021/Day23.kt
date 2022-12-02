package y2021

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Day23 {
  data class Burrow(val hallway: List<Char>, val rooms: Map<Char, List<Char>>, val cost: Int = 0) {

    private val costMagnitudes = mapOf('A' to 1, 'B' to 10, 'C' to 100, 'D' to 1000)
    private val roomEntrancePositions = mapOf('A' to 2, 'B' to 4, 'C' to 6, 'D' to 8)
    private val allowedStoppingSquares = (0..10).filterNot { roomEntrancePositions.containsValue(it) }

    fun isOrganized(): Boolean = rooms.all { (k, v) -> v.all { it == k } }

    fun findCandidateMoves(): List<Burrow> {
      val candidateMovesToRooms = hallway.withIndex()
          .filter { (_, amphipod) -> costMagnitudes.containsKey(amphipod) }
          .mapNotNull { (index, _) -> canMoveToRoom(index) }
      return candidateMovesToRooms.ifEmpty { roomEntrancePositions.flatMap { (room) -> canMoveOutOfRoom(room) } }
    }

    private fun canMoveToRoom(positionOfAmphipodInHallway: Int): Burrow? {
      val amphipod = hallway[positionOfAmphipodInHallway]
      val roomIsReady = rooms.getValue(amphipod).all { it == amphipod || it == ' ' }
      val noObstructions = canMoveToHallway(positionOfAmphipodInHallway, roomEntrancePositions.getValue(amphipod))
      return if (roomIsReady && noObstructions) newBurrow(positionOfAmphipodInHallway, ' ', amphipod, amphipod) else null
    }

    private fun canMoveOutOfRoom(roomType: Char): List<Burrow> {
      val roomIsFinishedOrEmpty = rooms.getValue(roomType).all { it == roomType || it == ' ' }
      if (roomIsFinishedOrEmpty) return emptyList()

      return allowedStoppingSquares.filter { canMoveToHallway(roomEntrancePositions.getValue(roomType), it) }
          .map { square -> newBurrow(square, rooms.getValue(roomType).first { it != ' ' }, roomType, ' ') }
    }

    private fun canMoveToHallway(start: Int, destination: Int): Boolean {
      val adjacent = if (start < destination) start + 1 else start - 1 // Exclude square housing the amphipod
      return (min(adjacent, destination)..max(adjacent, destination)).all { square -> hallway[square] == ' ' }
    }

    private fun newBurrow(hallwayIndex: Int, hallwayValue: Char, roomType: Char, roomValue: Char): Burrow {
      val a = if (hallwayValue == ' ') roomValue else hallwayValue
      val h = hallway.mapIndexed { i, a -> if (i == hallwayIndex) hallwayValue else a }
      val r = newRoom(roomType, roomValue)
      val c = abs(hallwayIndex - roomEntrancePositions.getValue(roomType)) + r.size - r.count { it != ' ' } + if (roomValue == ' ') 0 else 1
      return this.copy(hallway = h, rooms = rooms + (roomType to r), cost = cost + c * costMagnitudes.getValue(a))
    }
    private fun newRoom(roomType: Char, roomValue: Char): List<Char> {
      val prefix = if (roomValue == ' ') rooms.getValue(roomType).takeWhile { it == ' ' } else rooms.getValue(roomType).takeWhile { it == ' ' }.drop(1)
      val suffix = rooms.getValue(roomType).drop(prefix.size + 1)
      return prefix + roomValue + suffix
    }
  }

  fun partOne(lines: List<String>): Int {
    return findMinOrganization(parse(lines))
  }

  fun partTwo(lines: List<String>): Int {
    val burrow = parse(lines)
    val insert = mapOf('A' to listOf('D', 'D'), 'B' to listOf('C', 'B'), 'C' to listOf('B', 'A'), 'D' to listOf('A', 'C'))
    val expanded = burrow.copy(rooms = burrow.rooms.mapValues { (k, v) -> listOf(v.first()) + insert.getValue(k) + v.last() })
    return findMinOrganization(expanded)
  }

  private fun parse(lines: List<String>): Burrow {
    val amphipods = lines.drop(2).take(2).map { line -> line.filterNot { it == '#' } }
    val rooms = amphipods.first().indices.map { index -> amphipods.map { it[index] } }
    return Burrow(List(11) { ' ' }, mapOf('A' to rooms[0], 'B' to rooms[1], 'C' to rooms[2], 'D' to rooms[3]))
  }

  private fun findMinOrganization(burrow: Burrow, minCost: Int = Int.MAX_VALUE): Int {
    if (burrow.isOrganized()) return burrow.cost

    return burrow.findCandidateMoves()
      .filter { candidate -> candidate.cost < minCost }
      .fold(minCost) { min, candidate -> min(min, findMinOrganization(candidate, min)) }
  }
}