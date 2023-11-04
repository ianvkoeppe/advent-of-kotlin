package y2021

object Day3 {
  fun partOne(lines: List<String>): Long {
    val gamma = (0 until lines.first().length).map { i -> maxBitAtIndex(lines, i) }.joinToString("").toLong(2)
    return gamma * ((1L shl lines.first().length) - gamma - 1)
  }

  fun partTwo(lines: List<String>): Long {
    val oxygen = filterBinaryUntilOne(lines, Char::equals).toLong(2)
    val co2 = filterBinaryUntilOne(lines, { a, b -> a != b }).toLong(2)
    return oxygen * co2
  }

  private fun filterBinaryUntilOne(
    binaries: List<String>,
    comparator: (Char, Any?) -> Boolean,
    index: Int = 0
  ): String {
    if (binaries.size == 1) return binaries.first()

    val criteria = maxBitAtIndex(binaries, index)
    return filterBinaryUntilOne(binaries.filter { comparator(it[index], criteria) }, comparator, index + 1)
  }

  private fun maxBitAtIndex(binaries: List<String>, index: Int = 0): Char {
    return if (binaries.map { it[index] }.count { it == '1' } >= binaries.size / 2.0) '1' else '0'
  }
}
