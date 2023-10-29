package y2022

object Day3 {

  fun partOne(lines: List<String>): Int {
    return lines
      .map { rucksack ->
        findSharedChar(
          listOf(rucksack.take(rucksack.length / 2), rucksack.drop(rucksack.length / 2))
        )
      }
      .sumOf { toPriority(it) }
  }

  fun partTwo(lines: List<String>): Int {
    return lines.chunked(3).map { rucksacks -> findSharedChar(rucksacks) }.sumOf { toPriority(it) }
  }

  private fun findSharedChar(sacks: List<String>): Char {
    return sacks
      .reduce { candidates, sack -> candidates.toSet().intersect(sack.toSet()).joinToString("") }
      .first()
  }

  private fun toPriority(c: Char) =
    (if (c >= 'a') c.code - 'a'.code else c.code - 'A'.code + 26) + 1
}
