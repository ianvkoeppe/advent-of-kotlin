package y2023

object Day1 {
  private val numericDigits: Map<String, Int> = (1..9).associateBy(Int::toString)
  private val speltDigits: Map<String, Int> =
    listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine").zip(1..9).toMap()

  fun partOne(lines: List<String>): Int = lines.sumOf(::parseCalibrationValue)

  fun partTwo(lines: List<String>): Int =
    lines.sumOf { line -> parseCalibrationValue(line, numericDigits + speltDigits) }

  private fun parseCalibrationValue(line: String, allowableDigits: Map<String, Int> = numericDigits): Int {
    val first = allowableDigits.keys.filter(line::contains).minBy(line::indexOf)
    val last = allowableDigits.keys.maxBy(line::lastIndexOf)
    return "${allowableDigits.getValue(first)}${allowableDigits.getValue(last)}".toInt()
  }
}
