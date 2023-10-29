package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day23Tests {
  @Test
  fun partOneExample() {
    assertThat(Day23.partOne(Reader.readExample(2021, 23))).isEqualTo(12521)
  }

  @Test
  fun partOne() {
    assertThat(Day23.partOne(Reader.readProblem(2021, 23))).isEqualTo(14467)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day23.partTwo(Reader.readExample(2021, 23))).isEqualTo(44169)
  }

  @Test
  fun partTwo() {
    assertThat(Day23.partTwo(Reader.readProblem(2021, 23))).isEqualTo(48759)
  }
}
