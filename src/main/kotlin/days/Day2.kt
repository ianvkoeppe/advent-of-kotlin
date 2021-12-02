package days

import kotlin.math.abs

data class Position(val x: Long = 0, val y: Long = 0, val aim: Long = 0)

object Day2 {
  fun partOne(lines: List<String>): Long {
    val position = lines.fold(Position()) { position, instruction ->
      val (orientation, distance) = instruction.split(" ")
      when (orientation) {
        "forward" -> position.copy(x = position.x + distance.toInt())
        "up" -> position.copy(y = position.y + distance.toInt())
        "down" -> position.copy(y = position.y - distance.toInt())
        else -> position
      }
    }
    return abs(position.x * position.y)
  }

  fun partTwo(lines: List<String>): Long {
    val position = lines.fold(Position()) { position, instruction ->
      val (orientation, distance) = instruction.split(" ")
      when (orientation) {
        "forward" -> position.copy(x = position.x + distance.toInt(), y = position.y - (distance.toInt() * position.aim))
        "up" -> position.copy(aim = position.aim + distance.toInt())
        "down" -> position.copy(aim = position.aim - distance.toInt())
        else -> position
      }
    }
    return abs(position.x * position.y)
  }
}