package y2022

import kotlin.math.max
import kotlin.math.min

object Day14 {
  data class Cave(val walls: Set<Pair<Int, Int>>, val maxDepth: Int? = null, val lowestWall: Int = walls.maxOf { it.second }, val sand: MutableSet<Pair<Int, Int>> = mutableSetOf()) {
    fun placeSand(grain: Pair<Int, Int>): Cave = this.also { it.sand.add(grain) }
    fun isFallingIntoAbyss(sand: Pair<Int, Int>): Boolean = sand.second >= (maxDepth ?: lowestWall)
    fun findNextSettlePoint(sand: Pair<Int, Int>): Pair<Int, Int>? = settlePriorities.map { (x, y) -> sand.first + x to sand.second + y }.find { point -> !isOccupied(point) }
    private fun isOccupied(point: Pair<Int, Int>): Boolean = walls.contains(point) || sand.contains(point) || point.second == maxDepth
  }

  private val settlePriorities = listOf(0 to 1, -1 to 1, 1 to 1)
  private val sandStart = Pair(500, 0)

  fun partOne(lines: List<String>): Int = letItSandLetItSandLetItSand(parse(lines)).sand.size
  fun partTwo(lines: List<String>): Int {
    val cave = parse(lines)
    return letItSandLetItSandLetItSand(cave.copy(maxDepth = cave.lowestWall + 2)).sand.size
  }

  private fun parse(lines: List<String>): Cave {
    val walls = lines.flatMap { line ->
      line.split(" -> ")
        .map { point -> point.split(",").map { it.toInt() } }
        .map { (x, y) -> x to y }
        .windowed(2)
        .flatMap { (start, end) -> createWall(start, end) }
    }.toSet()
    return Cave(walls)
  }
  private fun createWall(start: Pair<Int, Int>, end: Pair<Int, Int>): List<Pair<Int, Int>> {
    val (minX, maxX) = min(start.first, end.first) to max(start.first, end.first)
    val (minY, maxY) = min(start.second, end.second) to max(start.second, end.second)
    return when (minX) { maxX -> (minY..maxY).map { minX to it } else -> (minX..maxX).map { it to minY } }
  }

  private tailrec fun letItSandLetItSandLetItSand(cave: Cave): Cave {
    val settled = settle(cave, sandStart)?.let(cave::placeSand)
    return if (settled != null) letItSandLetItSandLetItSand(settled) else cave
  }

  private tailrec fun settle(cave: Cave, sand: Pair<Int, Int>): Pair<Int, Int>? {
    val nextPoint = cave.findNextSettlePoint(sand)
    return when {
      cave.isFallingIntoAbyss(sand) || cave.sand.contains(sand) -> null
      nextPoint != null -> settle(cave, nextPoint)
      else -> sand
    }
  }
}
