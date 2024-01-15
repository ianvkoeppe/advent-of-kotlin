package y2023

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Day22 {
  private data class Coordinate(val x: Int, val y: Int, val z: Int) {
    fun adjacentCoordinates(): List<Coordinate> =
      setOf(-1, 0, 1).flatMap { adjX ->
        setOf(-1, 0, 1).flatMap { adjY ->
          setOf(-1, 0, 1)
            .filter { adjZ -> abs(adjX) + abs(adjY) + abs(adjZ) == 1 }
            .map { adjZ -> Coordinate(x + adjX, y + adjY, z + adjZ) }
        }
      }

    fun adjacentCoordinatesAbove(): List<Coordinate> = adjacentCoordinates().filter { it.z > z }

    fun adjacentCoordinatesBelow(): List<Coordinate> = adjacentCoordinates().filter { it.z < z }
  }

  private data class Brick(val start: Coordinate, val end: Coordinate) {
    fun minZ(): Int = min(start.z, end.z)

    fun dropBy(amount: Int) = Brick(start.copy(z = start.z - amount), end.copy(z = end.z - amount))

    fun coordinates(): List<Coordinate> =
      when {
        start.x != end.x -> (min(start.x, end.x)..max(start.x, end.x)).map { x -> Coordinate(x, start.y, start.z) }
        start.y != end.y -> (min(start.y, end.y)..max(start.y, end.y)).map { y -> Coordinate(start.x, y, start.z) }
        else -> (min(start.z, end.z)..max(start.z, end.z)).map { z -> Coordinate(start.x, start.y, z) }
      }

    fun adjacentCoordinatesAbove(): List<Coordinate> =
      coordinates().flatMap(Coordinate::adjacentCoordinatesAbove).filter { it.z > max(start.z, end.z) }

    fun adjacentCoordinatesBelow(): List<Coordinate> =
      coordinates().flatMap(Coordinate::adjacentCoordinatesBelow).filter { it.z < min(start.z, end.z) }
  }

  private class TowerOfBricks(private val bricks: List<Brick>) {
    companion object {
      fun parse(lines: List<String>): TowerOfBricks {
        val bricks =
          lines.map { line ->
            val (start, end) = line.split("~").map { it.split(",").map(String::toInt) }
            Brick(Coordinate(start[0], start[1], start[2]), Coordinate(end[0], end[1], end[2]))
          }
        return TowerOfBricks(bricks).letSettle()
      }
    }

    private val bricksByCoordinates = bricks.flatMap { brick -> brick.coordinates().map { c -> c to brick } }.toMap()
    private val bricksToThoseItSupports =
      bricks.associateWith { brick -> brick.adjacentCoordinatesAbove().mapNotNull { bricksByCoordinates[it] }.toSet() }
    private val brickToThoseItIsSupportedBy =
      bricks.associateWith { brick -> brick.adjacentCoordinatesBelow().mapNotNull { bricksByCoordinates[it] }.toSet() }

    private fun letSettle(): TowerOfBricks {
      val mins = mutableMapOf<Pair<Int, Int>, Int>()
      val fallen =
        bricks
          .sortedBy { min(it.start.z, it.end.z) }
          .map { brick ->
            val minPossibleZ = brick.coordinates().maxOf { coordinate -> (mins[coordinate.x to coordinate.y] ?: 0) + 1 }
            val dropped = brick.dropBy(brick.minZ() - minPossibleZ)
            mins.putAll(dropped.coordinates().associate { (it.x to it.y) to it.z })

            dropped
          }

      return TowerOfBricks(fallen)
    }

    fun countBricksSafeToDisintegrate(): Int = partitionBricksBySafeAndUnsafeForDisintegration().first.size

    private fun partitionBricksBySafeAndUnsafeForDisintegration(): Pair<List<Brick>, List<Brick>> {
      return bricks.partition { brick ->
        bricksToThoseItSupports.getValue(brick).all { b -> brickToThoseItIsSupportedBy.getValue(b).size > 1 }
      }
    }

    fun countFallingBlocksForEachDisintegration(): Int =
      partitionBricksBySafeAndUnsafeForDisintegration().second.sumOf { brick -> countFalling(setOf(brick)) }

    private fun countFalling(current: Set<Brick>, seen: Set<Brick> = setOf()): Int {
      if (current.isEmpty()) return 0

      val teetering =
        current
          .flatMap { brick ->
            val supporting = bricksToThoseItSupports.getValue(brick)
            supporting.filter { brickToThoseItIsSupportedBy.getValue(it).all { it in current || it in seen } }
          }
          .toSet()
      return teetering.size + countFalling(teetering, seen + current)
    }
  }

  fun partOne(lines: List<String>): Int = TowerOfBricks.parse(lines).countBricksSafeToDisintegrate()

  fun partTwo(lines: List<String>): Int = TowerOfBricks.parse(lines).countFallingBlocksForEachDisintegration()
}
