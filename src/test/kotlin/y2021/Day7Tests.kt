package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day7Tests {
  @Test
  fun partOneExample() {
    assertThat(Day7.partOne(Reader.readExample(2021, 7))).isEqualTo(37)
  }

  @Test
  fun partOne() {
    assertThat(Day7.partOne(Reader.readProblem(2021, 7))).isEqualTo(340987)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day7.partTwo(Reader.readExample(2021, 7))).isEqualTo(168)
  }

  @Test
  fun partTwo() {
    assertThat(Day7.partTwo(Reader.readProblem(2021, 7))).isEqualTo(96987874)
  }
}