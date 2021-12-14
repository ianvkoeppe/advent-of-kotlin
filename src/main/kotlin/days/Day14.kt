package days

import kotlin.math.ceil

object Day14 {

  fun partOne(lines: List<String>): Long {
    val (template, rules) = parse(lines)
    return findMaxMinDifferenceOfElementFrequenciesAfterSteps(template, rules, 10)
  }

  fun partTwo(lines: List<String>): Long {
    val (template, rules) = parse(lines)
    return findMaxMinDifferenceOfElementFrequenciesAfterSteps(template, rules, 40)
  }

  private fun parse(lines: List<String>): Pair<String, Map<String, String>> {
    return lines.first() to lines.drop(2).map { it.split(" -> ") }.associate { (a, b) -> a to b }
  }

  private fun findMaxMinDifferenceOfElementFrequenciesAfterSteps(template: String, rules: Map<String, String>, steps: Int): Long {
    val elementPairs = template.zipWithNext { a, b -> a.toString() + b }.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
    val counts = step(elementPairs, rules, steps)
      .flatMap { (element, count) -> listOf(element[0] to count, element[1] to count) }
      .fold(mapOf<Char, Long>()) { counts, (k, v) -> counts + (k to counts.getOrDefault(k, 0) + v) }
      .mapValues { (_, v) -> ceil(v / 2.0).toLong()  }.toMap()
    return counts.values.maxOrNull()!! - counts.values.minOrNull()!!
  }

  private fun step(polymer: Map<String, Long>, rules: Map<String, String>, step: Int): Map<String, Long> {
    if (step == 0) return polymer

    val next = polymer.flatMap { (element, count) ->
      listOf(element[0] + rules.getValue(element) to count, rules.getValue(element) + element[1] to count)
    }.fold(mapOf<String, Long>()) { counts, (k, v) -> counts + (k to counts.getOrDefault(k, 0) + v) }
    return step(next, rules, step - 1)
  }
}
