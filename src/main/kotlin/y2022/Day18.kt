package y2022

import kotlin.math.abs

object Day18 {
  data class Cube(val x: Int, val y: Int, val z: Int) {
    fun manhattanDistance(other: Cube): Int = abs(x - other.x) + abs(y - other.y) + abs(z - other.z)
  }

  data class AirPockets(val isInterior: Boolean, val pockets: Set<Cube>)

  data class PartitionedAirPockets(
    val interior: Set<Cube> = setOf(),
    val exterior: Set<Cube> = setOf()
  ) {
    fun add(pockets: AirPockets): PartitionedAirPockets =
      if (pockets.isInterior) copy(interior = interior + pockets.pockets)
      else copy(exterior = exterior + pockets.pockets)

    fun contains(pocket: Cube) = interior.contains(pocket) || exterior.contains(pocket)
  }

  data class LavaDroplet(val cubes: Set<Cube>) {
    private val adjacentCubes =
      listOf(
        Cube(-1, 0, 0),
        Cube(1, 0, 0),
        Cube(0, -1, 0),
        Cube(0, 1, 0),
        Cube(0, 0, -1),
        Cube(0, 0, 1)
      )
    private val min: Cube = Cube(cubes.minOf { it.x }, cubes.minOf { it.y }, cubes.minOf { it.z })
    private val max: Cube = Cube(cubes.maxOf { it.x }, cubes.maxOf { it.y }, cubes.maxOf { it.z })
    private val pockets =
      (min.x..max.x)
        .flatMap { x -> (min.y..max.y).flatMap { y -> (min.z..max.z).map { z -> Cube(x, y, z) } } }
        .filterNot(cubes::contains)
        .toSet()

    fun countNonOverlappingSides(): Int = 6 * cubes.size - countOverlappingSides()

    fun countNonOverlappingExternalSides(): Int = countNonOverlappingSides() - countInteriorSides()

    private fun countInteriorSides(): Int =
      countOverlappingSides(findPartitionedAirPockets().interior)

    private fun countOverlappingSides(others: Set<Cube> = cubes): Int =
      cubes.sumOf { cube -> others.count { other -> cube.manhattanDistance(other) == 1 } }

    private fun findPartitionedAirPockets(): PartitionedAirPockets {
      val external = findAccessibleAirPockets(findAllEdgeExternalAirPockets())
      val interior = pockets.filterNot(external::contains).toSet()
      return PartitionedAirPockets(interior, external)
    }

    private tailrec fun findAccessibleAirPockets(
      unseen: Set<Cube>,
      seen: Set<Cube> = unseen
    ): Set<Cube> {
      val neighbors =
        unseen
          .flatMap(::findAdjacentCubes)
          .filterNot(seen::contains)
          .filterNot(cubes::contains)
          .filter(pockets::contains)
          .toSet()
      if (neighbors.isEmpty()) return seen

      return findAccessibleAirPockets(neighbors, seen + neighbors)
    }

    private fun findAllEdgeExternalAirPockets(): Set<Cube> = pockets.filter(::isExternal).toSet()

    private fun findAdjacentCubes(cube: Cube): Set<Cube> =
      adjacentCubes
        .map { (adjX, adjY, adjZ) -> Cube(cube.x + adjX, cube.y + adjY, cube.z + adjZ) }
        .toSet()

    private fun isExternal(cube: Cube): Boolean =
      cube.x <= min.x ||
        cube.x >= max.x ||
        cube.y <= min.y ||
        cube.y >= max.y ||
        cube.z <= min.z ||
        cube.z >= max.z
  }

  fun partOne(lines: List<String>): Int = parse(lines).countNonOverlappingSides()

  fun partTwo(lines: List<String>): Int = parse(lines).countNonOverlappingExternalSides()

  private fun parse(lines: List<String>): LavaDroplet =
    LavaDroplet(
      lines
        .map { line -> line.split(",").map(String::toInt) }
        .map { (x, y, z) -> Cube(x, y, z) }
        .toSet()
    )
}
