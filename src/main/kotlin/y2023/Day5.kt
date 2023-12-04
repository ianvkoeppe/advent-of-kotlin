package y2023

object Day5 {
  private data class Item(val start: Long, val end: Long)

  private data class Rule(val start: Long, val destinationStart: Long, val length: Long) {
    private val end: Long = start + length - 1

    fun split(i: Item): Set<Item> =
      when {
        (i.end < start || i.start > end || (i.start >= start && i.end <= end)) -> setOf(i)
        i.start < start && i.end <= end -> setOf(Item(i.start, start - 1), Item(start, i.end))
        i.start >= start -> setOf(Item(i.start, end), Item(end + 1, i.end))
        else -> setOf(Item(i.start, start - 1), Item(start, end), Item(end + 1, i.end))
      }

    fun applyOrNull(v: Item): Item? = if (v.start >= start && v.end <= end) Item(apply(v.start), apply(v.end)) else null

    private fun apply(v: Long): Long = (v - start + destinationStart)
  }

  private data class Almanac(val items: Map<String, Set<Item>>, val rules: Map<String, Pair<String, Set<Rule>>>)

  fun partOne(lines: List<String>): Long = findMinLocation(lines) { it.map { start -> Item(start, start) } }

  fun partTwo(lines: List<String>): Long =
    findMinLocation(lines) { it.chunked(2).map { (start, end) -> Item(start, start + end - 1) } }

  private fun findMinLocation(lines: List<String>, seeder: (List<Long>) -> List<Item>): Long =
    produce(parse(lines, seeder)).items.getValue("location").minOf(Item::start)

  private fun parse(lines: List<String>, seeder: (List<Long>) -> List<Item>): Almanac {
    val items = mapOf("seed" to seeder(lines.first().substringAfter("seeds: ").split(" ").map(String::toLong)).toSet())
    val categories = lines.drop(2).joinToString("\n").split("\n\n")
    val rules =
      categories.associate { category ->
        val (sourceCategory, _, destinationCategory) = category.substringBefore(" map:").split("-")
        val rules =
          category
            .split("\n")
            .drop(1)
            .map {
              val (destinationStart, sourceStart, length) = it.split(" ").map(String::toLong)
              Rule(sourceStart, destinationStart, length)
            }
            .toSet()
        sourceCategory to (destinationCategory to rules)
      }
    return Almanac(items, rules)
  }

  private tailrec fun produce(almanac: Almanac): Almanac {
    if (almanac.items.size == 1 && almanac.items.containsKey("location")) {
      return almanac
    }

    val next =
      almanac.items
        .map { (category, items) ->
          val (destinationCategory, rules) = almanac.rules.getValue(category)

          val splits =
            items.flatMap { value -> rules.fold(setOf(value)) { splits, rule -> splits.flatMap(rule::split).toSet() } }
          val converted = splits.map { seed -> rules.firstNotNullOfOrNull { it.applyOrNull(seed) } ?: seed }.toSet()
          destinationCategory to converted
        }
        .toMap()
    return produce(almanac.copy(items = next))
  }
}
