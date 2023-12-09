package y2023

object Day9 {
  fun partOne(lines: List<String>): Int = findSumOfNextExtrapolatedValues(parse(lines))

  fun partTwo(lines: List<String>): Int = findSumOfNextExtrapolatedValues(parse(lines).map(List<Int>::reversed))

  private fun parse(lines: List<String>): List<List<Int>> = lines.map { it.split(" ").map(String::toInt) }

  private fun findSumOfNextExtrapolatedValues(histories: List<List<Int>>): Int = histories.sumOf(::extrapolateNextValue)

  private fun extrapolateNextValue(sequence: List<Int>): Int =
    when {
      sequence.all(0::equals) -> 0
      else -> extrapolateNextValue(sequence.zipWithNext().map { (f, s) -> s - f }) + sequence.last()
    }
}
