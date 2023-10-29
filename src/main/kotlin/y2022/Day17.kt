package y2022

object Day17 {
  data class Point(val x: Int, val y: Int)

  enum class TetrominoType {
    HORIZONTAL,
    CROSS,
    L,
    VERTICAL,
    BLOCK
  }

  data class Tetromino(val type: TetrominoType, val points: Set<Point>) {
    fun moveLeft(): Tetromino = move { (x, y) -> Point(x - 1, y) }

    fun moveRight(amount: Int = 1): Tetromino = move { (x, y) -> Point(x + amount, y) }

    fun moveDown(): Tetromino = move { (x, y) -> Point(x, y - 1) }

    fun moveUp(amount: Int): Tetromino = move { (x, y) -> Point(x, y + amount) }

    private fun move(transform: (Point) -> Point): Tetromino =
      copy(points = points.map(transform).toSet())
  }

  data class Placement(val tetromino: Tetromino, val direction: Int)

  data class Field(
    val grid: List<Placement> = listOf(),
    private val points: Set<Point> = grid.flatMap { it.tetromino.points }.toSet(),
    val width: Int = 7
  ) {
    fun place(tetromino: Tetromino, direction: Int): Field =
      copy(grid = grid + Placement(tetromino, direction), points = points + tetromino.points)

    fun canPlace(point: Point): Boolean =
      point.x in (0 until width) && point.y >= 0 && !points.contains(point)

    fun findCycle(): Field? {
      val tetromino = grid.lastOrNull()
      val candidateCycleEnd =
        grid.dropLast(1).indexOfLast {
          it.tetromino.type == tetromino?.tetromino?.type && it.direction == tetromino.direction
        }
      return if (
        candidateCycleEnd >= 0 && (candidateCycleEnd - (grid.size - candidateCycleEnd) + 1) > 0
      ) {
        val second = Field(grid.subList(candidateCycleEnd + 1, grid.size))
        val first =
          Field(grid.subList(candidateCycleEnd - second.grid.size + 1, candidateCycleEnd + 1))
        if (first.toString() == second.toString()) second else null
      } else null
    }

    fun height(): Int = (points.maxOfOrNull { it.y + 1 } ?: 0) - floor()

    private fun floor(): Int = points.minOfOrNull { it.y } ?: 0

    override fun toString(): String =
      (height() downTo floor()).joinToString("\n") { y ->
        (0 until width).joinToString("") { x -> if (points.contains(Point(x, y))) "#" else "." }
      }
  }

  data class Tetris(
    private val directions: List<Char>,
    val field: Field = Field(),
    private val d: Int = 0,
    private val t: Int = 0
  ) {
    private val horizontal =
      Tetromino(TetrominoType.HORIZONTAL, setOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0)))
    private val cross =
      Tetromino(
        TetrominoType.CROSS,
        setOf(Point(1, 0), Point(0, 1), Point(1, 1), Point(2, 1), Point(1, 2))
      )
    private val l =
      Tetromino(
        TetrominoType.L,
        setOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(2, 1), Point(2, 2))
      )
    private val vertical =
      Tetromino(TetrominoType.VERTICAL, setOf(Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3)))
    private val block =
      Tetromino(TetrominoType.BLOCK, setOf(Point(0, 0), Point(1, 0), Point(0, 1), Point(1, 1)))
    private val tetrominoes = listOf(horizontal, cross, l, vertical, block).map { it.moveRight(2) }

    fun settleNext(): Tetris = settle(tetrominoes[t].moveUp(field.height() + 3))

    private fun settle(tetromino: Tetromino): Tetris {
      val horizontal = moveHorizontalIfPossible(tetromino)
      val nextDirection = (d + 1) % directions.size

      return when {
        canMoveTo(horizontal.moveDown()) -> copy(d = nextDirection).settle(horizontal.moveDown())
        else ->
          copy(
            field = field.place(horizontal, d),
            d = nextDirection,
            t = (t + 1) % tetrominoes.size
          )
      }
    }

    private fun moveHorizontalIfPossible(tetromino: Tetromino): Tetromino =
      when {
        directions[d] == '<' && canMoveTo(tetromino.moveLeft()) -> tetromino.moveLeft()
        directions[d] == '>' && canMoveTo(tetromino.moveRight()) -> tetromino.moveRight()
        else -> tetromino
      }

    private fun canMoveTo(tetromino: Tetromino): Boolean = tetromino.points.all(field::canPlace)
  }

  fun partOne(lines: List<String>): Long =
    findHeightAfterDropping(Tetris(lines.first().toList()), 2022)

  fun partTwo(lines: List<String>): Long =
    findHeightAfterDropping(Tetris(lines.first().toList()), 1000000000000)

  private tailrec fun findHeightAfterDropping(
    tetris: Tetris,
    remaining: Long,
    heightViaCycles: Long = 0
  ): Long {
    if (remaining == 0L) return tetris.field.height().toLong() + heightViaCycles

    val dropped = tetris.settleNext()

    val cycle = if (heightViaCycles == 0L) tetris.field.findCycle() else null
    return if (cycle != null) {
      val viaCycles = (remaining / cycle.grid.size) * cycle.height()
      return findHeightAfterDropping(dropped, remaining % cycle.grid.size - 1, viaCycles)
    } else findHeightAfterDropping(dropped, remaining - 1, heightViaCycles)
  }
}
