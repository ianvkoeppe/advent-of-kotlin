package y2023

import java.math.BigDecimal

object Day24 {
  private data class Point(var x: BigDecimal, var y: BigDecimal, var z: BigDecimal) {
    fun hasAtLeastOneNonZero(): Boolean = x != BigDecimal.ZERO || y != BigDecimal.ZERO || z != BigDecimal.ZERO

    fun subtract(other: Point): Point = Point(x - other.x, y - other.y, z - other.z)

    fun crossProduct(other: Point): Point =
      Point(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x)

    fun dotProduct(other: Point): BigDecimal = x * other.x + y * other.y + z * other.z

    fun sum(): Long = (x + y + z).toLong()
  }

  private data class Plane(val point: Point, val perpendicularVector: BigDecimal)

  private data class Hail(val pos: Point, val velocity: Point) {
    companion object {
      fun parse(line: String): Hail {
        val (hail, velocity) =
          line.split("\\s+@\\s+".toRegex()).map { it.split(",\\s+".toRegex()).map(String::toBigDecimal) }
        return Hail(Point(hail[0], hail[1], hail[2]), Point(velocity[0], velocity[1], velocity[2]))
      }
    }

    fun intersectsXY(other: Hail, min: BigDecimal, max: BigDecimal): Boolean {
      val d = velocity.y * other.velocity.x - other.velocity.y * velocity.x
      if (d == BigDecimal.ZERO) return false

      val otherTime =
        ((other.pos.y * velocity.x) - (pos.y * velocity.x) + (velocity.y * pos.x) - (velocity.y * other.pos.x)) / d
      val time = (other.velocity.x * otherTime + other.pos.x - pos.x) / velocity.x
      if (time < BigDecimal.ZERO || otherTime < BigDecimal.ZERO) return false

      val (x, y) = velocity.x * time + pos.x to velocity.y * time + pos.y
      val (otherX, otherY) = other.velocity.x * otherTime + other.pos.x to other.velocity.y * otherTime + other.pos.y
      return x in min..max && y in min..max && otherX in min..max && otherY in min..max
    }

    fun isLinearlyIndependent(other: Hail): Boolean = velocity.crossProduct(other.velocity).hasAtLeastOneNonZero()

    fun findPlane(other: Hail): Plane {
      val sub = pos.subtract(other.pos)
      return Plane(
        sub.crossProduct(velocity.subtract(other.velocity)),
        sub.dotProduct(velocity.crossProduct(other.velocity)),
      )
    }
  }

  private class HailStorm(lines: List<String>) {
    private val hail: List<Hail> = lines.map(Hail::parse)

    fun countIntersectionsWithXY(min: BigDecimal, max: BigDecimal): Int =
      hail.withIndex().sumOf { (i, h) -> hail.drop(i + 1).count { h.intersectsXY(it, min, max) } }

    fun findPositionOfRock(): Point {
      val (first, second, third) = findLinearlyIndependentHail().take(3)
      val (firstPlane, secondPlane, thirdPlane) =
        listOf(first.findPlane(second), first.findPlane(third), second.findPlane(third))

      val perpendicularVectors =
        Point(firstPlane.perpendicularVector, secondPlane.perpendicularVector, thirdPlane.perpendicularVector)
      val dotProductOfPlanes = firstPlane.point.dotProduct(secondPlane.point.crossProduct(thirdPlane.point))
      val w =
        matrixMultiply(
            perpendicularVectors,
            secondPlane.point.crossProduct(thirdPlane.point),
            thirdPlane.point.crossProduct(firstPlane.point),
            firstPlane.point.crossProduct(secondPlane.point),
          )
          .let { (x, y, z) -> Point(x / dotProductOfPlanes, y / dotProductOfPlanes, z / dotProductOfPlanes) }
      val ww = first.velocity.subtract(w).crossProduct(second.velocity.subtract(w))

      val vectors =
        Point(
          ww.dotProduct(second.pos.crossProduct(second.velocity.subtract(w))),
          -ww.dotProduct(first.pos.crossProduct(first.velocity.subtract(w))),
          first.pos.dotProduct(ww),
        )
      val S = ww.dotProduct(ww)
      return matrixMultiply(vectors, first.velocity.subtract(w), second.velocity.subtract(w), ww).let { (x, y, z) ->
        Point(x / S, y / S, z / S)
      }
    }

    private fun findLinearlyIndependentHail(): List<Hail> =
      hail.fold(listOf()) { independent, h ->
        if (independent.all(h::isLinearlyIndependent)) independent + h else independent
      }

    private fun matrixMultiply(vectors: Point, a: Point, b: Point, c: Point): Point {
      val x = vectors.x * a.x + vectors.y * b.x + vectors.z * c.x
      val y = vectors.x * a.y + vectors.y * b.y + vectors.z * c.y
      val z = vectors.x * a.z + vectors.y * b.z + vectors.z * c.z
      return Point(x, y, z)
    }
  }

  fun partOne(lines: List<String>, min: Long, max: Long): Int =
    HailStorm(lines).countIntersectionsWithXY(min.toBigDecimal(), max.toBigDecimal())

  fun partTwo(lines: List<String>): Long = HailStorm(lines).findPositionOfRock().sum()
}
