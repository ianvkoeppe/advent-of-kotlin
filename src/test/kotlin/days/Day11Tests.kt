package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day11Tests {
  @Test
  fun partOneExample() {
    assertThat(Day11.partOne(Reader.read("src/test/resources/day11/example.txt"))).isEqualTo(1656)
  }

  @Test
  fun partOne() {
    assertThat(Day11.partOne(Reader.read("src/test/resources/day11/problem.txt"))).isEqualTo(1739)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day11.partTwo(Reader.read("src/test/resources/day11/example.txt"))).isEqualTo(195)
  }

  @Test
  fun partTwo() {
    assertThat(Day11.partTwo(Reader.read("src/test/resources/day11/problem.txt"))).isEqualTo(324)
  }
}