package y2023

import common.Point
import java.util.stream.Collectors
import kotlin.math.pow

object Day21 {
  private class Garden(val grid: List<List<Char>>, val isInBounds: (Point) -> Boolean) {
    companion object {
      private val emptySquares: Set<Char> = setOf('.', 'S')

      fun bounded(lines: List<String>): Garden {
        val grid = lines.map(String::toList)
        return Garden(grid) { (x, y) -> y >= 0 && y < grid.size && x >= 0 && x < grid[y].size }
      }

      fun unbounded(lines: List<String>): Garden = Garden(lines.map(String::toList)) { _ -> true }
    }

    private val start: Point =
      grid.indices.flatMap { y -> grid[y].indices.map { x -> Point(x, y) } }.first { (x, y) -> grid[y][x] == 'S' }

    fun countPossibleSquaresOccupiedAfterSteps(n: Int): Long {
      val runningSteps = mutableListOf<Int>()

      return (1..n)
        .fold(setOf(start)) { current, step ->
          val next = findNextSteps(grid, current)
          runningSteps.add(next.size)

          if (step >= grid.size) {
            val differences = runningSteps.zipWithNext().map { (f, s) -> s - f }
            val lastFewCycles = differences.takeLast(3 * grid.size)
            val diffsAcrossCycles =
              lastFewCycles
                .withIndex()
                .groupBy(keySelector = { it.index % grid.size }, valueTransform = { it.value })
                .mapValues { it.value.zipWithNext().map { (f, s) -> s - f } }

            if (diffsAcrossCycles.values.all { it.size > 1 && it.takeLast(2).distinct().size == 1 }) {
              val remainingCycles = (n - step) / grid.size
              val lastCycle = lastFewCycles.takeLast(grid.size)

              return diffsAcrossCycles.entries.sumOf { (i, constantDifference) ->
                val x = if (i < (n - step) % grid.size) remainingCycles + 1 else remainingCycles

                (constantDifference.last() * (x.toDouble().pow(2) + x) / 2 + (lastCycle[i] * x)).toLong()
              } + runningSteps.last()
            }
          }

          next
        }
        .size
        .toLong()
    }

    private fun findNextSteps(grid: List<List<Char>>, current: Set<Point>): Set<Point> {
      return current
        .parallelStream()
        .flatMap { point ->
          point.neighbors().stream().filter(isInBounds).filter { (x, y) ->
            val inY = (if (y >= 0) y else ((y % grid.size) + grid.size)) % grid.size
            val inX = (if (x >= 0) x else ((x % grid[inY].size) + grid[inY].size)) % grid[inY].size
            grid[inY][inX] in emptySquares
          }
        }
        .collect(Collectors.toSet())
    }
  }

  fun partOne(lines: List<String>, steps: Int): Long =
    Garden.bounded(lines).countPossibleSquaresOccupiedAfterSteps(steps)

  fun partTwo(lines: List<String>, steps: Int): Long =
    Garden.unbounded(lines).countPossibleSquaresOccupiedAfterSteps(steps)
}
