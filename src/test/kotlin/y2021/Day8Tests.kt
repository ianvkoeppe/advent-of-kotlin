package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day8Tests {
  @Test
  fun partOneExample() {
    assertThat(Day8.partOne(Reader.readExample(2021, 8))).isEqualTo(26)
  }

  @Test
  fun partOne() {
    assertThat(Day8.partOne(Reader.readProblem(2021, 8))).isEqualTo(330)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day8.partTwo(Reader.readExample(2021, 8))).isEqualTo(61229)
  }

  @Test
  fun partTwo() {
    assertThat(Day8.partTwo(Reader.readProblem(2021, 8))).isEqualTo(1010472)
  }
}