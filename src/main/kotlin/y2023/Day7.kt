package y2023

object Day7 {
  private data class Hand(val cards: List<Char>, val bid: Int) : Comparable<Hand> {
    private val ranks: Map<Char, Int> =
      listOf('*', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A')
        .withIndex()
        .associate { it.value to it.index }
        .toMap()
    private val counts: Map<Char, Int> = cards.groupingBy { it }.eachCount()
    private val countsWithoutJokers: List<Int> = counts.filterKeys { it != '*' }.values.sortedDescending()

    override fun compareTo(other: Hand): Int {
      return compareBy<Hand> { (it.countsWithoutJokers.firstOrNull() ?: 0) + (it.counts['*'] ?: 0) }
        .thenBy { it.countsWithoutJokers.drop(1).firstOrNull() ?: 0 }
        .then { first, second ->
          val (f, s) = first.cards.zip(second.cards).dropWhile { (f, s) -> f == s }.first()
          ranks.getValue(f).compareTo(ranks.getValue(s))
        }
        .compare(this, other)
    }
  }

  fun partOne(lines: List<String>): Int = findWinnings(lines)

  fun partTwo(lines: List<String>): Int = findWinnings(lines, mapOf('J' to '*'))

  private fun findWinnings(lines: List<String>, mapper: Map<Char, Char> = mapOf()): Int =
    lines.map { line -> parse(line, mapper) }.sorted().mapIndexed { index, hand -> hand.bid * (index + 1) }.sum()

  private fun parse(line: String, mapper: Map<Char, Char>): Hand {
    val (hand, bid) = line.split(" ")
    return Hand(hand.map { mapper[it] ?: it }, bid.toInt())
  }
}
