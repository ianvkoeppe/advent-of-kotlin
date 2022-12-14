package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day14Tests {
  @Test
  fun partOneExample() {
    assertThat(Day14.partOne(Reader.readExample(2022, 14))).isEqualTo(24)
  }

  @Test
  fun partOne() {
    assertThat(Day14.partOne(Reader.readProblem(2022, 14))).isEqualTo(888)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day14.partTwo(Reader.readExample( 2022, 14))).isEqualTo(93)
  }

  @Test
  fun partTwo() {
    assertThat(Day14.partTwo(Reader.readProblem(2022, 14))).isEqualTo(26461)
  }
}