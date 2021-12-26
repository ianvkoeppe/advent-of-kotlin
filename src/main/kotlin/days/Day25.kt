package days

object Day25 {

  fun partOne(lines: List<String>): Int = stepUntilStuck(parse(lines))

  private fun parse(lines: List<String>): List<List<Char>> {
    return lines.map { line -> line.map { it } }
  }

  private fun stepUntilStuck(cucumbers: List<List<Char>>, steps: Int = 0): Int {
    val moved = move(move(cucumbers, '>', 0, 1), 'v', 1, 0)
    return if (moved == cucumbers) steps - 1 else stepUntilStuck(moved, steps + 1)
  }

  private fun move(cucumbers: List<List<Char>>, target: Char, x: Int, y: Int): List<List<Char>> {
    val moved = cucumbers.map { it.toMutableList() }.toMutableList()
    cucumbers.forEachIndexed { rowIndex, row ->
      row.forEachIndexed { colIndex, cucumber ->
        if (cucumber == target && cucumbers[(rowIndex + x) % cucumbers.size][(colIndex + y) % cucumbers.size] == '.') {
          moved[(rowIndex + x) % cucumbers.size][(colIndex + y) % cucumbers.size] = cucumber
          moved[rowIndex][colIndex] = '.'
        }
      }
    }
    return moved
  }
}