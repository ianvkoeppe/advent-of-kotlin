package y2022

object Day4 {
  fun partOne(lines: List<String>): Int {
    return findOverlapsWithCriteria(lines) { f, s -> f.first <= s.first && f.last >= s.last }
  }

  fun partTwo(lines: List<String>): Int {
    return findOverlapsWithCriteria(lines) { f, s -> f.first <= s.first && f.last >= s.first }
  }

  private fun findOverlapsWithCriteria(lines: List<String>, criteria: (IntRange, IntRange) -> Boolean): Int {
    return lines.count { line ->
      val (first, second) = line.split(",").map(Day4::parseRange)
      criteria(first, second) || criteria(second, first)
    }
  }

  private fun parseRange(range: String): IntRange {
    val (start, end) = range.split("-").map { it.toInt() }
    return start..end
  }
}
