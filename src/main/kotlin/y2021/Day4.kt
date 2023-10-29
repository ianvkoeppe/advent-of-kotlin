package y2021

typealias Board = List<List<Long>>

object Day4 {
  fun partOne(lines: List<String>): Long {
    val (nums, boards) = parse(lines)
    val (winningRound, board) =
      boards.map { board -> findMinWinRoundBy(board, nums) to board }.minByOrNull { it.first }!!
    return calculateBoardScore(board, nums, winningRound)
  }

  fun partTwo(lines: List<String>): Long {
    val (nums, boards) = parse(lines)
    val (winningRound, board) =
      boards.map { board -> findMinWinRoundBy(board, nums) to board }.maxByOrNull { it.first }!!
    return calculateBoardScore(board, nums, winningRound)
  }

  private fun parse(lines: List<String>): Pair<LinkedHashSet<Long>, List<Board>> {
    val nums = lines.first().split(",").map(String::toLong).toCollection(linkedSetOf())
    return nums to parseBoards(lines.drop(2))
  }

  private fun parseBoards(lines: List<String>, index: Int = 0): List<Board> {
    val board: Board =
      (index until lines.size)
        .takeWhile { lines[it].isNotEmpty() }
        .map { lines[it].trim().split("\\s+".toRegex()).map(String::toLong) }

    return if (board.isEmpty()) listOf()
    else listOf(board) + parseBoards(lines, index + board.size + 1)
  }

  private fun findMinWinRoundBy(board: Board, nums: LinkedHashSet<Long>): Int {
    val minRowWin =
      board.indices
        .map { checkWin(board, nums, it to 0) { (x, y) -> x to y + 1 } }
        .filterNotNull()
        .minOrNull()
    val minColWin =
      board.indices
        .map { checkWin(board, nums, 0 to it) { (x, y) -> x + 1 to y } }
        .filterNotNull()
        .minOrNull()
    return listOfNotNull(minRowWin, minColWin).minOrNull()!!
  }

  private fun checkWin(
    board: Board,
    nums: LinkedHashSet<Long>,
    start: Pair<Int, Int> = (0 to 0),
    inc: (Pair<Int, Int>) -> Pair<Int, Int>
  ): Int? {
    val hitRounds =
      generateSequence(start, inc)
        .takeWhile { (x, y) -> x >= 0 && y >= 0 && x < board.size && y < board.size }
        .map { (x, y) -> board[x][y] }
        .map(nums::indexOf)
        .filter { it != -1 }
        .toList()
    return if (hitRounds.size == board.size) hitRounds.maxOrNull() else null
  }

  private fun calculateBoardScore(
    board: Board,
    nums: LinkedHashSet<Long>,
    winningRound: Int
  ): Long {
    val winningNums = nums.take(winningRound + 1).toCollection(linkedSetOf())
    val sumOfUnMarkedSquares =
      (board.indices).sumOf { row ->
        (board.indices).sumOf { col ->
          if (winningNums.contains(board[row][col])) 0 else board[row][col]
        }
      }
    return sumOfUnMarkedSquares * winningNums.last()
  }
}
