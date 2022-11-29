package y2021

object Day1 {
  fun partOne(lines: List<String>): Int {
    return lines.map { it.toInt() }.zipWithNext().count { (current, next) -> next > current }
  }

  fun partTwo(lines: List<String>): Int {
    return lines.map { it.toInt() }.windowed(3).map { (a, b, c) -> a + b + c }
      .zipWithNext()
      .count { (current, next) -> next > current }
  }
}