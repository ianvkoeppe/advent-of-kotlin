package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day11Tests {
  @Test
  fun partOneExample() {
    assertThat(Day11.partOne(Reader.readExample(2021, 11))).isEqualTo(1656)
  }

  @Test
  fun partOne() {
    assertThat(Day11.partOne(Reader.readProblem(2021, 11))).isEqualTo(1739)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day11.partTwo(Reader.readExample(2021, 11))).isEqualTo(195)
  }

  @Test
  fun partTwo() {
    assertThat(Day11.partTwo(Reader.readProblem(2021, 11))).isEqualTo(324)
  }
}