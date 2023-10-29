package y2021

import io.Reader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5Tests {
  @Test
  fun partOneExample() {
    assertThat(Day5.partOne(Reader.readExample(2021, 5))).isEqualTo(5)
  }

  @Test
  fun partOne() {
    assertThat(Day5.partOne(Reader.readProblem(2021, 5))).isEqualTo(6007)
  }

  @Test
  fun partTwoExample() {
    assertThat(Day5.partTwo(Reader.readExample(2021, 5))).isEqualTo(12)
  }

  @Test
  fun partTwo() {
    assertThat(Day5.partTwo(Reader.readProblem(2021, 5))).isEqualTo(19349)
  }
}
