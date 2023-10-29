package y2021

object Day8 {
  data class Signal(val segments: List<String>, val value: List<String>)

  private val numberToSegmentCount =
    mapOf(
      (0 to 6),
      (1 to 2),
      (2 to 5),
      (3 to 5),
      (4 to 4),
      (5 to 5),
      (6 to 6),
      (7 to 3),
      (8 to 7),
      (9 to 6)
    )
  private val uniqueSegmentCounts =
    numberToSegmentCount.entries
      .filter { (number, _) ->
        numberToSegmentCount.values.count { it == numberToSegmentCount[number] } == 1
      }
      .associate { (number, count) -> count to number }

  fun partOne(lines: List<String>): Int {
    return parse(lines)
      .map { it.value }
      .sumOf { v -> v.count { uniqueSegmentCounts.containsKey(it.length) } }
  }

  fun partTwo(lines: List<String>): Int {
    return parse(lines).sumOf { signal ->
      val decoded = decodeSignals(signal)
      signal.value.map { v -> decoded[v] }.joinToString("").toInt()
    }
  }

  private fun parse(lines: List<String>): List<Signal> {
    return lines
      .map { it.split(" | ") }
      .map { (segments, value) -> Signal(parseSegments(segments), parseSegments(value)) }
  }

  private fun parseSegments(segments: String): List<String> {
    return segments.split(" ").map { it.toSortedSet().joinToString("") }
  }

  private fun decodeSignals(signal: Signal): Map<String, Int> {
    val one = signal.segments.find { it.length == numberToSegmentCount[1] }!!
    val three =
      signal.segments.find { s -> s.length == numberToSegmentCount[3] && one.all { it in s } }!!
    val four = signal.segments.find { it.length == numberToSegmentCount[4] }!!
    val six =
      signal.segments.find { s ->
        s.length == numberToSegmentCount[6] && one.count { it in s } == 1
      }!!
    val five =
      signal.segments.find { s ->
        s.length == numberToSegmentCount[5] && six.count { it in s } == 5
      }!!
    val two =
      signal.segments.find { s -> s.length == numberToSegmentCount[2] && s != three && s != five }!!
    val seven = signal.segments.find { it.length == numberToSegmentCount[7] }!!
    val eight = signal.segments.find { it.length == numberToSegmentCount[8] }!!
    val nine =
      signal.segments.find { s ->
        s.length == numberToSegmentCount[9] && s.count { three.contains(it) } == 5
      }!!
    val zero =
      signal.segments.find { s -> s.length == numberToSegmentCount[0] && s != six && s != nine }!!
    return listOf(zero, one, two, three, four, five, six, seven, eight, nine)
      .mapIndexed { index, segment -> segment to index }
      .toMap()
  }
}
