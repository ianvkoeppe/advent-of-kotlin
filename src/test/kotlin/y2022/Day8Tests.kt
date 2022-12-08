package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day8Tests {
  @Test
  fun partOneExample() {
    assertThat(Day8.partOne(Reader.readExample(2022, 8))).isEqualTo(21)
  }

  @Test
  fun partOne() {
    assertThat(Day8.partOne(Reader.readProblem(2022, 8))).isEqualTo(1698)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day8.partTwo(Reader.readExample(2022, 8))).isEqualTo(8)
  }

  @Test
  fun partTwo() {
    assertThat(Day8.partTwo(Reader.readProblem(2022, 8))).isEqualTo(672280)
  }
}