package y2022

object Day6 {
  fun partOne(lines: List<String>): Int = findPacketStart(lines.first(), 4)

  fun partTwo(lines: List<String>): Int = findPacketStart(lines.first(), 14)

  private fun findPacketStart(line: String, lengthOfMarker: Int): Int =
    line.windowed(lengthOfMarker).takeWhile { it.toSet().size != lengthOfMarker }.size + lengthOfMarker
}
