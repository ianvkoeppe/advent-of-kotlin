package y2021

import java.util.*

object Day20 {
  private val surroundingSquares =
    listOf(
      (-1 to -1),
      (-1 to 0),
      (-1 to 1),
      (0 to -1),
      (0 to 0),
      (0 to 1),
      (1 to -1),
      (1 to 0),
      (1 to 1)
    )

  fun partOne(lines: List<String>): Int {
    val (algorithm, image) = parse(lines)
    return step(image, algorithm, 2).values.flatMap { it.values }.count { it == '#' }
  }

  fun partTwo(lines: List<String>): Int {
    val (algorithm, image) = parse(lines)
    return step(image, algorithm, 50).values.flatMap { it.values }.count { it == '#' }
  }

  private fun parse(lines: List<String>): Pair<String, SortedMap<Int, SortedMap<Int, Char>>> {
    val algorithm = lines.first()
    val image =
      lines
        .drop(2)
        .mapIndexed { row, line ->
          row to line.mapIndexed { column, char -> column to char }.toMap().toSortedMap()
        }
        .toMap()
        .toSortedMap()
    return algorithm to image
  }

  private fun step(
    image: Map<Int, Map<Int, Char>>,
    algorithm: String,
    step: Int
  ): Map<Int, Map<Int, Char>> {
    if (step == 0) return image

    val outside =
      if (algorithm.first() == '#') (if (step % 2 == 0) algorithm.last() else algorithm.first())
      else '.'
    val newImage =
      (image.keys.first() - 1..image.keys.last() + 1)
        .associateWith { rowIndex ->
          (image.keys.first() - 1..image.keys.last() + 1)
            .associateWith { columnIndex ->
              val binary =
                surroundingSquares
                  .map { (x, y) -> rowIndex + x to columnIndex + y }
                  .map { (x, y) -> image[x]?.get(y) ?: outside }
                  .map { if (it == '#') 1 else 0 }
                  .joinToString("")
                  .toInt(2)

              algorithm[binary]
            }
            .toSortedMap()
        }
        .toSortedMap()

    return step(newImage, algorithm, step - 1)
  }
}
