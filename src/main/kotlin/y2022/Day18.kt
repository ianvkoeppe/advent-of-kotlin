package y2022

import kotlin.math.abs

object Day18 {
  data class Cube(val x: Int, val y: Int, val z: Int) {
    fun manhattanDistance(other: Cube): Int = abs(x - other.x) + abs(y - other.y) + abs(z - other.z)
  }
  data class AirPockets(val isInterior: Boolean, val pockets: Set<Cube>)
  data class PartitionedAirPockets(val interior: Set<Cube> = setOf(), val exterior: Set<Cube> = setOf()) {
    fun add(pockets: AirPockets): PartitionedAirPockets =
      if (pockets.isInterior) copy(interior = interior + pockets.pockets) else copy(exterior = exterior + pockets.pockets)
    fun contains(pocket: Cube) = interior.contains(pocket) || exterior.contains(pocket)
  }

  data class LavaDroplet(val cubes: Set<Cube>) {
    private val adjacentCubes = listOf(Cube(-1, 0, 0), Cube(1, 0, 0), Cube(0, -1, 0), Cube(0, 1, 0), Cube(0, 0, -1), Cube(0, 0, 1))
    private val min: Cube = Cube(cubes.minOf { it.x }, cubes.minOf { it.y }, cubes.minOf { it.z })
    private val max: Cube = Cube(cubes.maxOf { it.x }, cubes.maxOf { it.y }, cubes.maxOf { it.z })

    fun countNonOverlappingSides(): Int = 6 * cubes.size - countOverlappingSides()
    fun countNonOverlappingExternalSides(): Int = countNonOverlappingSides() - countInteriorSides()
    private fun countInteriorSides(): Int = countOverlappingSides(findPartitionedAirPockets().interior)
    private fun countOverlappingSides(others: Set<Cube> = cubes): Int = cubes.sumOf { cube -> others.count { other -> cube.manhattanDistance(other) == 1 } }

    private fun findPartitionedAirPockets(): PartitionedAirPockets = findAllAirPockets().fold(PartitionedAirPockets()) { pockets, pocket ->
      if (pockets.contains(pocket)) pockets else pockets.add(findPartitionedAirPockets(pocket))
    }
    private fun findPartitionedAirPockets(pocket: Cube, pockets: AirPockets = AirPockets(true, setOf(pocket))): AirPockets {
      if (isExternal(pocket)) return pockets.copy(isInterior = false)

      val neighbors = findAdjacentCubes(pocket).filterNot(cubes::contains).filterNot(pockets.pockets::contains)
      if (neighbors.isEmpty()) return pockets.copy(isInterior = true)

      return neighbors.fold(pockets) { p, neighbor ->
        val partitioned = findPartitionedAirPockets(neighbor, p.copy(pockets = p.pockets + neighbor))
        p.copy(isInterior = p.isInterior && partitioned.isInterior, pockets = partitioned.pockets)
      }
    }

    private fun findAllAirPockets(): Set<Cube> = (min.x..max.x).flatMap { x -> (min.y..max.y).flatMap { y -> (min.z..max.z).map { z -> Cube(x, y, z) } } }
      .filterNot(cubes::contains).toSet()
    private fun findAdjacentCubes(cube: Cube): Set<Cube> = adjacentCubes.map { (adjX, adjY, adjZ) -> Cube(cube.x + adjX, cube.y + adjY, cube.z + adjZ) }.toSet()
    private fun isExternal(cube: Cube): Boolean = cube.x <= min.x || cube.x >= max.x || cube.y <= min.y || cube.y >= max.y || cube.z <= min.z || cube.z >= max.z
  }

  fun partOne(lines: List<String>): Int = parse(lines).countNonOverlappingSides()
  fun partTwo(lines: List<String>): Int = parse(lines).countNonOverlappingExternalSides()

  private fun parse(lines: List<String>): LavaDroplet = LavaDroplet(lines.map { line -> line.split(",").map(String::toInt) }.map { (x, y, z) -> Cube(x, y, z) }.toSet())
}