package y2023

object Day15 {
  private data class Lens(val label: String, val add: Boolean = true, val focalLength: Int? = null) {
    companion object {
      fun withFullText(line: String) = Lens(line)

      fun withParsedLabel(line: String): Lens {
        val (label, maybeFocalLength) = line.split("[-=]".toRegex())
        return Lens(label, '=' in line, if (maybeFocalLength.isNotEmpty()) maybeFocalLength.toInt() else null)
      }
    }

    override fun hashCode(): Int = label.toList().fold(0) { hash, c -> ((hash + c.code) * 17) % 256 }

    override fun equals(other: Any?): Boolean = other is Lens && label == other.label
  }

  private class LensSet(private val table: Map<Int, Map<String, Lens>> = mutableMapOf()) {
    fun add(v: Lens): LensSet =
      LensSet(table + (v.hashCode() to table.getOrElse(v.hashCode()) { linkedMapOf() } + (v.label to v)))

    fun remove(v: Lens): LensSet =
      LensSet(table + (v.hashCode() to table.getOrElse(v.hashCode()) { linkedMapOf() } - v.label))

    fun focusPower(): Int =
      table.entries.sumOf { (box, lenses) ->
        (box + 1) * lenses.values.withIndex().sumOf { (index, lens) -> (index + 1) * (lens.focalLength ?: 1) }
      }
  }

  fun partOne(lines: List<String>): Int = lines.first().split(",").map(Lens::withFullText).sumOf(Lens::hashCode)

  fun partTwo(lines: List<String>): Int {
    return lines
      .first()
      .split(",")
      .map(Lens::withParsedLabel)
      .fold(LensSet()) { lenses, lens -> if (lens.add) lenses.add(lens) else lenses.remove(lens) }
      .focusPower()
  }
}
