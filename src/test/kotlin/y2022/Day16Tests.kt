package y2022

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day16Tests {
  @Test
  fun partOneExample() {
    assertThat(Day16.partOne(Reader.readExample(2022, 16))).isEqualTo(1651)
  }

  @Test
  fun partOne() {
    assertThat(Day16.partOne(Reader.readProblem(2022, 16))).isEqualTo(1986)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day16.partTwo(Reader.readExample(2022, 16))).isEqualTo(1707)
  }

  @Test
  @Disabled("1m 4s")
  fun partTwo() {
    assertThat(Day16.partTwo(Reader.readProblem(2022, 16))).isEqualTo(2464)
  }
}
