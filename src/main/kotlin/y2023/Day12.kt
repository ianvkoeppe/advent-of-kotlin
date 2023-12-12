package y2023

object Day12 {
  private data class ConditionRecord(
    val springs: String,
    val layout: List<Int>,
    val isCountingSprings: Boolean = false
  )

  fun partOne(lines: List<String>): Long = parse(lines).sumOf(::countArrangements)

  fun partTwo(lines: List<String>): Long = parse(lines, 5).sumOf(::countArrangements)

  private fun parse(lines: List<String>, unfold: Int = 1): List<ConditionRecord> =
    lines.map { line ->
      val (springs, contiguousLayout) = line.split(" ")
      val unfoldedSprings = List(unfold) { springs }.joinToString("?")
      val unfoldedLayout = List(unfold) { contiguousLayout }.joinToString(",").split(",").map(String::toInt)
      ConditionRecord(unfoldedSprings, unfoldedLayout)
    }

  private val cache: MutableMap<ConditionRecord, Long> = mutableMapOf()

  private fun countArrangements(record: ConditionRecord): Long {
    return when {
      record.springs.startsWith("?") -> resolveQuestionMark(record).sumOf(::countArrangements)
      record.springs.isEmpty() && (record.layout in setOf(listOf(), listOf(0))) -> 1
      record.springs.isEmpty() -> 0
      record.springs[0] == '#' && (record.layout.firstOrNull() ?: 0) == 0 -> 0
      record.springs[0] == '.' && record.layout.firstOrNull() != 0 && record.isCountingSprings -> 0
      else -> {
        val layout =
          when {
            record.springs[0] == '#' -> listOf(record.layout[0] - 1) + record.layout.drop(1)
            record.springs[0] == '.' && record.isCountingSprings -> record.layout.drop(1)
            else -> record.layout
          }

        val next =
          record.copy(springs = record.springs.drop(1), layout = layout, isCountingSprings = record.springs[0] == '#')
        cache.getOrPut(next) { countArrangements(next) }
      }
    }
  }

  private fun resolveQuestionMark(record: ConditionRecord): List<ConditionRecord> {
    val candidates = setOf(".${record.springs.drop(1)}", "#${record.springs.drop(1)}")
    return candidates.map { springs -> record.copy(springs = springs) }
  }
}
