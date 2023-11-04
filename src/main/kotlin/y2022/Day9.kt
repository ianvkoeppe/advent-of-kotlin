package y2022

import kotlin.math.abs

object Day9 {
  data class Movement(val direction: Char, val amount: Int)

  data class Position(val x: Int, val y: Int)

  data class KnottedRope(val head: Position, val tails: List<Position>)

  private val surroundingSquares =
    listOf((1 to 0), (1 to -1), (0 to -1), (-1 to -1), (-1 to 0), (-1 to 1), (0 to 1), (1 to 1))

  fun partOne(lines: List<String>): Int = move(parse(lines)).map { it.tails.first() }.distinct().count()

  fun partTwo(lines: List<String>): Int = move(parse(lines)).map { it.tails.last() }.distinct().count()

  private fun parse(lines: List<String>): List<Movement> =
    lines.map { it.split(" ") }.map { (distance, amount) -> Movement(distance.first(), amount.toInt()) }

  private fun move(movements: List<Movement>, numberOfKnots: Int = 9): List<KnottedRope> {
    val rope = KnottedRope(Position(0, 0), List(numberOfKnots) { Position(0, 0) })
    return movements.fold(listOf(rope)) { initial, instruction ->
      (1..instruction.amount).fold(initial) { positions, _ -> positions + moveOnce(positions.last(), instruction) }
    }
  }

  private fun moveOnce(rope: KnottedRope, movement: Movement): KnottedRope {
    val movedHead =
      when (movement.direction) {
        'U' -> rope.copy(head = rope.head.copy(x = rope.head.x, y = rope.head.y + 1))
        'R' -> rope.copy(head = rope.head.copy(x = rope.head.x + 1, y = rope.head.y))
        'D' -> rope.copy(head = rope.head.copy(x = rope.head.x, y = rope.head.y - 1))
        else -> rope.copy(head = rope.head.copy(x = rope.head.x - 1, y = rope.head.y))
      }
    return resolveTailPositions(movedHead)
  }

  private fun resolveTailPositions(position: KnottedRope): KnottedRope {
    val tails = position.tails.fold(listOf(position.head)) { next, tail -> next + resolvePosition(next.last(), tail) }
    return KnottedRope(position.head, tails.drop(1))
  }

  private fun resolvePosition(lead: Position, follower: Position): Position {
    if (isTouching(lead, follower)) return follower

    val (x, y) =
      surroundingSquares
        .map { (adjX, adjY) -> follower.x + adjX to follower.y + adjY }
        .filter { (x, y) -> isTouching(lead, Position(x, y)) }
        .minByOrNull { (x, y) -> manhattanDistance(lead, Position(x, y)) }!!
    return Position(x, y)
  }

  private fun isTouching(f: Position, s: Position): Boolean = abs(f.x - s.x) <= 1 && abs(f.y - s.y) <= 1

  private fun manhattanDistance(f: Position, s: Position): Int = abs(f.x - s.x) + abs(f.y - s.y)
}
