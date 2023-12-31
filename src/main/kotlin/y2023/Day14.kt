package y2023

object Day14 {
  private data class Platform(private val rocks: List<List<Char>>) {
    companion object {
      fun parse(lines: List<String>): Platform = Platform(lines.map(String::toMutableList).toMutableList())
    }

    tailrec fun cycle(times: Int, current: Platform = this, cache: Map<Platform, Int> = mapOf()): Platform {
      return when {
        times == 0 -> this
        current in cache -> {
          val start = cache.getValue(current)
          val remaining = times % (start - times)
          cache.filterValues { it == start - remaining }.keys.first()
        }
        else -> {
          val next = current.tiltNorth().tiltWest().tiltSouth().tiltEast()
          cycle(times - 1, next, cache + (current to times))
        }
      }
    }

    fun tiltNorth(): Platform = rocks.first().indices.fold(this) { p, x -> p.putCol(x, shift(getCol(x))) }

    private fun tiltWest(): Platform = rocks.indices.fold(this) { p, y -> p.putRow(y, shift(getRow(y))) }

    private fun tiltSouth(): Platform =
      rocks.first().indices.fold(this) { p, x -> p.putCol(x, shift(getCol(x).reversed()).reversed()) }

    private fun tiltEast(): Platform =
      rocks.indices.fold(this) { p, y -> p.putRow(y, shift(getRow(y).reversed()).reversed()) }

    private fun shift(vals: String): String {
      return when {
        vals.isEmpty() -> ""
        vals.first() == '.' && 'O' in vals.takeWhile { it != '#' } -> shift("O${vals.drop(1).replaceFirst('O', '.')}")
        else -> vals.first() + shift(vals.drop(1))
      }
    }

    fun value(): Int = rocks.mapIndexed { y, row -> (rocks.size - y) * row.count('O'::equals) }.sum()

    private fun getCol(x: Int): String = rocks.indices.map { y -> rocks[y][x] }.joinToString("")

    private fun putCol(x: Int, col: String): Platform =
      copy(rocks = rocks.mapIndexed { y, row -> row.take(x) + col[y] + row.drop(x + 1) })

    private fun getRow(y: Int): String = rocks[y].joinToString("")

    private fun putRow(y: Int, row: String): Platform =
      copy(rocks = rocks.take(y) + listOf(row.toList()) + rocks.drop(y + 1))
  }

  fun partOne(lines: List<String>): Int = Platform.parse(lines).tiltNorth().value()

  fun partTwo(lines: List<String>): Int = Platform.parse(lines).cycle(1000000000).value()
}
