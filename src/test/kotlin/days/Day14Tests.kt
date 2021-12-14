package days

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day14Tests {
  @Test
  fun partOneExamples() {
    assertThat(Day14.partOne(Reader.read("src/test/resources/day14/example.txt"))).isEqualTo(1588)
  }

  @Test
  fun partOne() {
    assertThat(Day14.partOne(Reader.read("src/test/resources/day14/problem.txt"))).isEqualTo(3831)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day14.partTwo(Reader.read("src/test/resources/day14/example.txt"))).isEqualTo(2188189693529L)
  }

  @Test
  fun partTwo() {
    assertThat(Day14.partTwo(Reader.read("src/test/resources/day14/problem.txt"))).isEqualTo(5725739914282L)
  }
}