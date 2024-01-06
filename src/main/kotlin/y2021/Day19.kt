package y2021

import kotlin.math.abs

object Day19 {
  private data class Point3D(val x: Int, val y: Int, val z: Int)

  private data class Scanner(val number: Int, val beacons: Set<Point3D>)

  private data class LocatedScanner(val number: Int, val beacons: Set<Point3D>, val position: Point3D)

  private val identity: (Point3D) -> Point3D = { it }
  private val xRotation: (Point3D) -> Point3D = { (x, y, z) -> Point3D(x, -z, y) }
  private val xRotations = generateSequence(identity) { next -> { p -> xRotation(next(p)) } }.take(4).toList()
  private val yRotation: (Point3D) -> Point3D = { (x, y, z) -> Point3D(-z, y, x) }
  private val yRotations =
    generateSequence(yRotation) { next -> { p -> yRotation(yRotation(next(p))) } }.take(2).toList()
  private val zRotation: (Point3D) -> Point3D = { (x, y, z) -> Point3D(-y, x, z) }
  private val zRotations = generateSequence(identity) { next -> { p -> zRotation(next(p)) } }.take(4).toList()
  private val rotations: List<(Point3D) -> Point3D> =
    (xRotations + yRotations).flatMap { orient -> zRotations.map { rotate -> { point -> rotate(orient(point)) } } }

  fun partOne(lines: List<String>): Int {
    return determineAbsolutePositions(parse(lines)).flatMap { it.beacons }.toSet().count()
  }

  fun partTwo(lines: List<String>): Int {
    val scanners = determineAbsolutePositions(parse(lines))
    return scanners
      .mapIndexed { index, first ->
        scanners
          .drop(index)
          .map { second -> diff(first.position, second.position) }
          .maxOf { diff -> abs(diff.x) + abs(diff.y) + abs(diff.z) }
      }
      .maxOrNull() ?: 0
  }

  private fun parse(lines: List<String>): List<Scanner> {
    if (lines.isEmpty()) return emptyList()

    val number = lines.first().removeSurrounding("--- scanner ", " ---").toInt()
    val beacons =
      lines
        .drop(1)
        .takeWhile { it != "" }
        .map { line ->
          val (x, y, z) = line.split(",")
          Point3D(x.toInt(), y.toInt(), z.toInt())
        }
        .toSet()
    return listOf(Scanner(number, beacons)) + parse(lines.drop(beacons.size + 2))
  }

  private fun determineAbsolutePositions(
    scanners: List<Scanner>,
    unvisited: List<Scanner> = scanners.drop(1),
    next: List<LocatedScanner> =
      listOf(LocatedScanner(scanners.first().number, scanners.first().beacons, Point3D(0, 0, 0)))
  ): List<LocatedScanner> {
    if (unvisited.isEmpty()) return next

    val scannerToLocated =
      unvisited
        .map { u -> u to next.asSequence().mapNotNull { n -> locateScanner(n, u) }.firstOrNull() }
        .filterNot { it.second == null }
        .toMap()

    return determineAbsolutePositions(
      scanners,
      unvisited - scannerToLocated.keys,
      next + scannerToLocated.values.filterNotNull()
    )
  }

  private fun locateScanner(first: LocatedScanner, second: Scanner): LocatedScanner? {
    for (transposed in transpose(second.beacons)) {
      for (f in first.beacons) {
        val firstRelativeDistances = findDistanceRelativeToPoint(first.beacons, f)
        for (s in transposed) {
          val secondRelativeDistances = findDistanceRelativeToPoint(transposed, s)

          val overlapPairs =
            firstRelativeDistances.filterKeys(secondRelativeDistances::containsKey).map { (k, v) ->
              v to secondRelativeDistances.getValue(k)
            }
          if (overlapPairs.size >= 12) {
            val (firstOverlap, secondOverlap) = overlapPairs.first()
            val position = diff(firstOverlap, secondOverlap)
            return LocatedScanner(
              second.number,
              transposed.map { b -> Point3D(b.x + position.x, b.y + position.y, b.z + position.z) }.toSet(),
              position
            )
          }
        }
      }
    }

    return null
  }

  private fun transpose(points: Set<Point3D>): List<Set<Point3D>> {
    return rotations.map { rotate -> points.map { point -> rotate(point) }.toSet() }
  }

  private fun findDistanceRelativeToPoint(points: Set<Point3D>, point: Point3D): Map<Point3D, Point3D> {
    return points.fold(mapOf(Point3D(0, 0, 0) to point)) { all, next -> all + (diff(point, next) to next) }
  }

  private fun diff(f: Point3D, s: Point3D): Point3D = Point3D(f.x - s.x, f.y - s.y, f.z - s.z)
}
