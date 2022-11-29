package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Tests {
  @Test
  fun partOneExample() {
    assertThat(Day1.partOne(Reader.readExample(2021, 1))).isEqualTo(7)
  }

  @Test
  fun partOne() {
    assertThat(Day1.partOne(Reader.readProblem(2021, 1))).isEqualTo(1184)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day1.partTwo(Reader.readExample(2021, 1))).isEqualTo(5)
  }

  @Test
  fun partTwo() {
    assertThat(Day1.partTwo(Reader.readProblem(2021, 1))).isEqualTo(1158)
  }
}