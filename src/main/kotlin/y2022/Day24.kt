package y2022

object Day24 {
  private data class Position(val x: Int, val y: Int)

  private data class Valley(
    val tiles: Map<Position, Set<Char>>,
    val minutes: Int = 0,
    val width: Int = tiles.maxOf { it.key.x },
    val height: Int = tiles.maxOf { it.key.y }
  ) {
    private val start = Position(1, 0)
    private val end = Position(width - 1, height)
    private val adjacentSquares = listOf((1 to 0), (0 to 1), (-1 to 0), (0 to -1))
    private val movements: Map<Char, (Position, Int, Int) -> Position> =
      mapOf(
        '^' to { position, _, height -> position.copy(y = bound(position.y - 1, 1, height - 1)) },
        '>' to { position, width, _ -> position.copy(x = bound(position.x + 1, 1, width - 1)) },
        'v' to { position, _, height -> position.copy(y = bound(position.y + 1, 1, height - 1)) },
        '<' to { position, width, _ -> position.copy(x = bound(position.x - 1, 1, width - 1)) }
      )

    fun crossSafely(positions: Set<Position> = setOf(start), end: Position = this.end): Valley {
      if (positions.any { it == end }) return this

      val blizzard = blizzard()
      val next = (positions.flatMap { findPossibleMoves(it) } + positions).filter { blizzard.isSafe(it) }.toSet()
      return blizzard.crossSafely(next, end)
    }

    fun crossBack(): Valley = crossSafely(setOf(end), start)

    private fun blizzard(): Valley =
      copy(
        tiles =
          groupByPosition(
            tiles.flatMap { (position, blizzards) ->
              blizzards.map { blizzard ->
                if (blizzard in movements) blizzard(position, blizzard) to blizzard else position to blizzard
              }
            }
          ),
        minutes = minutes + 1
      )

    private fun blizzard(position: Position, blizzard: Char): Position =
      movements.getValue(blizzard)(position, width, height)

    private fun findPossibleMoves(position: Position): Set<Position> {
      return adjacentSquares
        .map { (adjX, adjY) -> Position(position.x + adjX, position.y + adjY) }
        .filter { (x, y) -> x in 1 until width && y in 0..height }
        .toSet()
    }

    private fun isSafe(position: Position): Boolean = tiles[position]?.all { it == '.' } ?: true

    private fun bound(index: Int, min: Int, max: Int): Int =
      (max - min + 1).let { range -> ((index - min) % range + range) % range + min }
  }

  fun partOne(lines: List<String>): Int = parse(lines).crossSafely().minutes

  fun partTwo(lines: List<String>): Int = parse(lines).crossSafely().crossBack().crossSafely().minutes

  private fun parse(lines: List<String>): Valley {
    return Valley(
      groupByPosition(lines.flatMapIndexed { y, row -> row.mapIndexed { x, blizzard -> Position(x, y) to blizzard } })
    )
  }

  private fun groupByPosition(blizzards: List<Pair<Position, Char>>): Map<Position, Set<Char>> =
    blizzards
      .groupBy { it.first }
      .mapValues { (_, positionsWithSymbols) -> positionsWithSymbols.map { it.second }.toSet() }
}
