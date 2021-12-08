package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day8Tests {
  @Test
  fun partOneExample() {
    assertThat(Day8.partOne(Reader.read("src/test/resources/day8/example.txt"))).isEqualTo(26)
  }

  @Test
  fun partOne() {
    assertThat(Day8.partOne(Reader.read("src/test/resources/day8/problem.txt"))).isEqualTo(330)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day8.partTwo(Reader.read("src/test/resources/day8/example.txt"))).isEqualTo(61229)
  }

  @Test
  fun partTwo() {
    assertThat(Day8.partTwo(Reader.read("src/test/resources/day8/problem.txt"))).isEqualTo(0)
  }
}