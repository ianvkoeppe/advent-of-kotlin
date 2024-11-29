package y2022

import kotlin.math.pow

object Day25 {
  private val snafuSymbolValues = mapOf('-' to -1, '=' to -2)
  private val numberToSymbol = mapOf(3 to '=', 4 to '-', 5 to '0')

  fun partOne(lines: List<String>): String = toSnafu(lines.map(::fromSnafu).sum())

  private fun fromSnafu(snafu: String): Long {
    return snafu
      .map { char -> snafuSymbolValues.getOrElse(char) { char.digitToInt() } }
      .reversed()
      .foldIndexed(0L) { place, total, digit -> total + digit * 5.toDouble().pow(place.toDouble()).toLong() }
  }

  private data class SnafuBuilder(val snafu: List<Char> = listOf(), val carryOver: Int = 0) {
    override fun toString(): String = snafu.joinToString("")
  }

  private fun toSnafu(n: Long): String {
    val base5Digits = generateSequence(n) { it / 5 }.takeWhile { it > 0 }.map { (it % 5).toInt() }.toList()
    return base5Digits.fold(SnafuBuilder(), ::toSnafuDigit).toString()
  }

  private fun toSnafuDigit(builder: SnafuBuilder, digit: Int): SnafuBuilder {
    val digitWithCarryOver = digit + builder.carryOver
    val carryOver = if (numberToSymbol.contains(digitWithCarryOver)) 1 else 0
    return builder.copy(
      snafu = listOf(numberToSymbol.getOrDefault(digitWithCarryOver, digitWithCarryOver.digitToChar())) + builder.snafu,
      carryOver = carryOver,
    )
  }
}
