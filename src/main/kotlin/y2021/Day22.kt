package y2021

import kotlin.math.max
import kotlin.math.min

object Day22 {
  data class Area3D(val x: LongRange, val y: LongRange, val z: LongRange)

  data class Instruction(val cmd: String, val area: Area3D)

  fun partOne(lines: List<String>): Long {
    val instructions = parse(lines).filter { withinFifty(it.area) }
    return run(instructions).sumOf { area ->
      (area.x.last - area.x.first + 1) * (area.y.last - area.y.first + 1) * (area.z.last - area.z.first + 1)
    }
  }

  private fun withinFifty(area: Area3D): Boolean {
    val ranges = listOf(area.x, area.y, area.z)
    return ranges.all { it.first >= -50 } && ranges.all { it.last <= 50 }
  }

  fun partTwo(lines: List<String>): Long {
    return run(parse(lines)).sumOf { area ->
      (area.x.last - area.x.first + 1) * (area.y.last - area.y.first + 1) * (area.z.last - area.z.first + 1)
    }
  }

  private fun parse(lines: List<String>): List<Instruction> {
    return lines.map { line ->
      val (cmd, ranges) = line.split(" ")
      val (xRange, yRange, zRange) = ranges.split(",")
      Instruction(cmd, Area3D(parseRange(xRange), parseRange(yRange), parseRange(zRange)))
    }
  }

  private fun parseRange(range: String): LongRange {
    val (low, high) = range.substringAfter("=").split("..")
    return low.toLong()..high.toLong()
  }

  private tailrec fun run(
    instructions: List<Instruction>,
    current: Int = 0,
    on: List<Area3D> = listOf()
  ): List<Area3D> {
    if (current == instructions.size) return on
    return run(instructions, current + 1, turn(on, instructions[current]))
  }

  private fun turn(current: List<Area3D>, i: Instruction): List<Area3D> {
    val sliced =
      current.flatMap { area ->
        val (onX, onY, onZ) = intersections(area, i.area)
        if (!onX.isEmpty() && !onY.isEmpty() && !onZ.isEmpty()) {
          val xSlices = slice(area.x, onX).map { range -> Area3D(range, area.y, area.z) }
          val remainingAreaForY = Area3D(onX.first..onX.last, area.y, area.z)
          val ySlices = slice(area.y, onY).map { range -> Area3D(remainingAreaForY.x, range, remainingAreaForY.z) }
          val remainingAreaForZ = Area3D(onX.first..onX.last, onY.first..onY.last, area.z)
          val zSlices = slice(area.z, onZ).map { range -> Area3D(remainingAreaForZ.x, remainingAreaForZ.y, range) }

          xSlices + ySlices + zSlices
        } else listOf(area)
      }
    return sliced + if (i.cmd == "on") listOf(i.area) else emptyList()
  }

  private fun intersections(first: Area3D, second: Area3D): Triple<LongRange, LongRange, LongRange> =
    Triple(intersect(first.x, second.x), intersect(first.y, second.y), intersect(first.z, second.z))

  private fun intersect(first: LongRange, second: LongRange): LongRange =
    max(first.first, second.first)..min(first.last, second.last)

  private fun slice(original: LongRange, intersection: LongRange): List<LongRange> {
    val first = if (intersection.first > original.first) original.first until intersection.first else LongRange.EMPTY
    val last = if (original.last > intersection.last) intersection.last + 1..original.last else LongRange.EMPTY
    return listOf(first, last).filterNot { it.isEmpty() }
  }
}
